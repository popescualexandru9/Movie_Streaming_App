package com.example.moviestreamingapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class StreamingMovieActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_streaming);

//        VideoView implementation. Unfortunately, couldn't find compatible links to stream online.
//        videoView = findViewById(R.id.streaming_view);
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);
//
//        Uri uri = Uri.parse("https://web.law.duke.edu/cspd/contest/videos/Framed-Contest_Documentaries-and-You.mp4");
//        videoView.setVideoURI(uri);
//        videoView.start();

        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_streaming, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(BuildConfig.GOOGLE_API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Toast.makeText(this, "Initialized Youtube Player successfully",Toast.LENGTH_LONG).show();

        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

//        if(!wasRestored){
//            youTubePlayer.cueVideo();
//        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initializing YouTube player {%1$s}" , youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    private final YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onStopped() {
        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    private final YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onVideoStarted() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
