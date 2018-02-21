package musicInfo;

import java.util.HashMap;

public class Song {
    String songName;
    String pathToSong;
    HashMap<String, String> paths = new HashMap<>();
    public Song (String songName, String pathToSong) {
        this.songName = songName;
        this.pathToSong = pathToSong;
    }

    public String getSongName() {
        return songName;
    }

    public String getPathToSong () {
        return pathToSong;
    }

    public HashMap<String, String> getPaths() {
        return paths;
    }
}
