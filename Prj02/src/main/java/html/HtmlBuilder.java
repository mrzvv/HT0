package html;

import musicInfo.Album;
import musicInfo.Artist;
import musicInfo.Song;

import java.util.HashMap;

public class HtmlBuilder {
    public StringBuffer newHtmlString (HashMap<String, Artist> artists) {
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "<meta charset=\"1251\">\n" +
        "<title>MP3 INFO</title>\n" +
        "</head>\n" +
        "<body>\n");
        for (Artist a : artists.values()) {
            sb.append("<p>ARTIST " + a.getArtistName() + "</p>");
            for (Album b : a.getAlbums().values()) {
                sb.append("<p>ALBUM " + b.getAlbumName() + "</p>");
                for (Song s : b.getSongs().values()) {
                    sb.append("<p>" + s.getSongName() + "\t<a href=\"" + s.getPathToSong() + "\">listen</a>\n" + "</p>");
                }
            }
            sb.append("<p>-----------------------------------------------------------------------------------------</p>");
        }
        sb.append("</body>\n" +
        "</html>");
        return sb;
    }
}
