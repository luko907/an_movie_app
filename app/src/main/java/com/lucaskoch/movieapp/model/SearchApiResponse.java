package com.lucaskoch.movieapp.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;

public class SearchApiResponse {

    List<SearchArrayObject> results = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<SearchArrayObject> getResults() {
        List<SearchArrayObject> filteredResults = results.stream()
                .filter(element -> element.getPoster_path().length() > 45
                        && element.getBackdrop_path().length() >45
                        && element.getVote_count() > 60
                        && !element.getRelease_date().isEmpty()
                        && !element.getOverview().isEmpty()
                        && !element.getTitle().isEmpty())
                .collect(Collectors.toList());

        /*Log.v("Tag","filteredResults =" + filteredResults);*/
        return filteredResults;
    }

    public void setResults(List<SearchArrayObject> results) {
        this.results = results;
    }
}
