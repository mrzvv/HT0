package musicInfo;

import java.util.HashMap;

public class Artist {
    String artistName;
    HashMap<String, Album> albums = new HashMap<>();

    public Artist (String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public HashMap<String, Album> getAlbums() {
        return albums;
    }
}
