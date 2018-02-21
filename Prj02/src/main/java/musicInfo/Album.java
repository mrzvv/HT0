package musicInfo;

import java.util.HashMap;

public class Album {
    String albumName;
    HashMap<String, Song> songs = new HashMap<>();

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public HashMap<String, Song> getSongs() {
        return songs;
    }
}
