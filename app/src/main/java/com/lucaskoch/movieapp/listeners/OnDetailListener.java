package com.lucaskoch.movieapp.listeners;


import com.lucaskoch.movieapp.model.SearchArrayObject;

public interface OnDetailListener {
    void onResponseInfo(SearchArrayObject response);
    void onErrorInfo(String message);
}
