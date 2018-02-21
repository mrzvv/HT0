package log4j;

import musicInfo.Album;
import musicInfo.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class MP3Logger {
    public void duplicates(HashMap<String, Artist> artists) {
        Logger logger = LogManager.getLogger();
        for (Artist a : artists.values()) {
            for (Album b : a.getAlbums().values()) {
                for (int i = 0; i < b.getSongs().values().size(); i++) {
                    if (b.getSongs().values().contains(b.getSongs().get(i+1))) {
                        logger.info("The duplicate has been found.");
                    }
                }
            }
        }
    }
}
