package net.anapsil.videoplayer.ui.player;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import net.anapsil.videoplayer.R;
import net.anapsil.videoplayer.player.AudioSyncEventListener;
import net.anapsil.videoplayer.player.VideoSyncEventListener;
import net.anapsil.videoplayer.ui.base.viewmodel.BaseViewModel;

/**
 * @author anapsil
 * @since 1.0
 */

public class PlayerViewModel extends BaseViewModel {
    public ObservableBoolean isLoading = new ObservableBoolean(false);
    private SimpleExoPlayer audioPlayer;
    private SimpleExoPlayer videoPlayer;

    private DataSource.Factory dataSourceFactory;
    private ExtractorsFactory extractorsFactory;

    public PlayerViewModel(Context context) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory audioTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(audioTrackSelectionFactory);

        audioPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        videoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

        dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(R.string.app_name)));
        extractorsFactory = new DefaultExtractorsFactory();
    }

    protected void loadData() {
    }

    public SimpleExoPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public SimpleExoPlayer getVideoPlayer() {
        return videoPlayer;
    }

    public void prepareAudioPlayer(String url) {
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url), dataSourceFactory, extractorsFactory, null, null);
        audioPlayer.prepare(mediaSource);
        audioPlayer.addListener(new AudioSyncEventListener(videoPlayer) {
            @Override
            public void onLoadingChanged(boolean isLoading) {
                super.onLoadingChanged(isLoading);
                PlayerViewModel.this.isLoading.set(isLoading);
            }
        });
    }

    public void prepareVideoPlayer(String url) {
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url), dataSourceFactory, extractorsFactory, null, null);
        videoPlayer.setPlayWhenReady(true);
        videoPlayer.prepare(new LoopingMediaSource(mediaSource));
        videoPlayer.addListener(new VideoSyncEventListener(audioPlayer));
    }

    @Override
    public void onDestroyView() {
        audioPlayer.release();
        videoPlayer.release();
        super.onDestroyView();
    }
}
