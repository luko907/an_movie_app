package com.lucaskoch.movieapp.listeners;

import com.lucaskoch.movieapp.model.Video;

public interface OnSearchVideoListener {
    void onResponseInfo(Video response);
    void onErrorInfo(String message);
}
