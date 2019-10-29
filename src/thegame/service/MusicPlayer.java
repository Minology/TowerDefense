package thegame.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

/**
 * Created by Minology on 10:34 CH
 */
public class MusicPlayer {
    private MediaPlayer player;

    public MusicPlayer(String path, boolean infiniteLoop) {
        try {
            final URL resource = getClass().getResource(path);
            Media media = new Media(resource.toString());
            player = new MediaPlayer(media);
            if (infiniteLoop) {
                player.setAutoPlay(true);
                player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        player.play();
        player.seek(Duration.ZERO);
    }

    public void stop() {
        player.stop();
    }
}
