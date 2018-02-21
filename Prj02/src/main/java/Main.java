import html.HtmlBuilder;
import log4j.MP3Logger;
import musicInfo.Album;
import musicInfo.Artist;
import musicInfo.Song;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        /*if (args.length == 0) {
            System.out.println("This program works as mp3 cataloguiser. To run it, please enter the paths of\n" +
                    "directories you'd like to add to catalogue as parameters.\n" +
                    "Example: java Main d:/dir1 f:/solder5/dir3");
        }*/

        System.setProperty("log4j.configurationFile", "log4j2.xml"); // settings for log configuration file

        LinkedHashSet<String> data = new LinkedHashSet<>(); //creating LinkedHashSet to exclude duplicity of paths
        String patternForDirectories = "\\w:[\\/\\w]*"; //setting a format for valid directory name
        Pattern pDir = Pattern.compile(patternForDirectories);

        for (int i = 0; i < args.length; i++) {
            Matcher mDir = pDir.matcher(args[i]);
            if (mDir.matches()) {
                data.add(args[i]);
            } else {
                System.out.println("Incorrect directory name: " + args[i]);
                System.out.println("Example for correct directory name: c:/dir1");
                return;
            }
        }

        data.add("d:/presentations");
        for (String path : data) {
            File folder = new File(path); //getting directory by path
            File[] listOfFiles = folder.listFiles(); //getting the array of files and directories in chosen directory

            if (listOfFiles != null) {
                File htmlData = new File("mp3info.html"); //creating an HTML file which'll store the data
                try {
                    StringBuffer s = new Main().getInfo(listOfFiles);
                    FileWriter fw = new FileWriter(htmlData, false); //opening new stream for writing in file ("false" means re-writing the file)
                    fw.write(s.toString());
                    fw.close();
                } catch (NullPointerException e) {
                    System.out.println("There are no mp3 files in directory " + path);
                }
            }
        }
    }

    private static byte[] readFileSegment(File file, int index) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        byte[] buffer = new byte[128];
        try {
            raf.skipBytes(index);
            raf.read(buffer, 0, 128);
            return buffer;
        } finally {
            raf.close();
        }
    }

    private StringBuffer getInfo(File[] files) throws IOException {
        HashMap<String, Artist> artists = new HashMap<>();

        for (int i = 0; i < files.length; i++) {
            Path j = Paths.get(String.valueOf(files[i])); //setting a path to [i] file/directory
            String path = j.toString();
            if (files[i].isDirectory()) {
                getInfo(files[i].listFiles()); //recursion for directories to get info from inner folders
            } else if (files[i].isFile() && new Main().isMP3(files[i].getName())) {
                byte[] array = Files.readAllBytes(j);
                int ind = array.length - 129;
                byte[] info = readFileSegment(files[i], ind);
                String id3 = new String(info);
                String title = id3.substring(4, 33).trim();
                if (title.length() == 0) {
                    title = "Default";
                }
                String artist = id3.substring(34, 63).trim();
                if (artist.length() == 0) {
                    artist = "Default";
                }
                String album = id3.substring(64, 93).trim();
                if (album.length() == 0) {
                    album = "Default";
                }

                Artist ar = new Artist(artist);
                artists.putIfAbsent(ar.getArtistName(), ar);
                Artist art = artists.get(ar.getArtistName());

                Album al = new Album(album);
                art.getAlbums().putIfAbsent(al.getAlbumName(), al);
                Album alb = art.getAlbums().get(al.getAlbumName());

                Song sg = new Song(title, path);
                alb.getSongs().putIfAbsent(sg.getSongName(), sg);
            }
        }
        new MP3Logger().duplicates(artists);
        StringBuffer strb = new HtmlBuilder().newHtmlString(artists);
        return strb;
    }

    private boolean isMP3(String path) { //this function checks the equality of found file to MP3 format
        if (path.endsWith("mp3")) {
            return true;
        }
        return false;
    }
}



