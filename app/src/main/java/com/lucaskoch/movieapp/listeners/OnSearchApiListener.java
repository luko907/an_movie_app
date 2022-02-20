package com.lucaskoch.movieapp.listeners;

import com.lucaskoch.movieapp.model.SearchApiResponse;

public interface OnSearchApiListener {

    void onResponseInfo(SearchApiResponse response);
    void onErrorInfo(String message);
}

