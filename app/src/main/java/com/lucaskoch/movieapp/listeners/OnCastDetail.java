package com.lucaskoch.movieapp.listeners;

import com.lucaskoch.movieapp.model.CastCrew;


public interface OnCastDetail {
    void onResponseInfo(CastCrew response);
    void onErrorInfo(String message);
}
