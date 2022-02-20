package com.lucaskoch.movieapp;

import androidx.annotation.RequiresApi;



import android.os.Build;
import android.os.Bundle;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.lucaskoch.movieapp.listeners.OnSearchVideoListener;
import com.lucaskoch.movieapp.model.Video;
import com.lucaskoch.movieapp.utils.YouTubeConfig;

public class TrailerActivity extends YouTubeBaseActivity {

    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    RequestManager videoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_play);
        String movie_id = getIntent().getStringExtra("data");

        videoManager = new RequestManager(this);
        videoManager.searchVideo(videoListener,movie_id);
    }

    private final OnSearchVideoListener videoListener = new OnSearchVideoListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onResponseInfo(Video response) {
            showVideo(response.getResults());
        }

        @Override
        public void onErrorInfo(String message) {

        }
    };

    void showVideo(String key) {

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);

                youTubePlayer.loadVideo(key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);
    }
}

