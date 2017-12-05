package net.anapsil.videoplayer.player;

import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.Player;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class SyncControlDispatcher extends DefaultControlDispatcher {
    private final Player audioPlayer;
    private final Player videoPlayer;

    public SyncControlDispatcher(Player audioPlayer, Player videoPlayer) {
        this.audioPlayer = audioPlayer;
        this.videoPlayer = videoPlayer;
    }

    @Override
    public boolean dispatchSetPlayWhenReady(Player player, boolean playWhenReady) {
        audioPlayer.setPlayWhenReady(playWhenReady);
        videoPlayer.setPlayWhenReady(playWhenReady);
        return true;
    }
}
