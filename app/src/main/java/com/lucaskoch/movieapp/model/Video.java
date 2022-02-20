package com.lucaskoch.movieapp.model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Video {

    List<VideosModel> results = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getResults() {
        List<VideosModel> filteredKey = results.stream().filter(element -> element.getType().equals("Trailer") && element.getKey()!= null).limit(1).collect(Collectors.toList());
        List<String> names = new ArrayList<>();

        for (VideosModel obj: filteredKey) {
            names.add(obj.getKey());
        }
     /*   Log.v("Tag", "response " + results.toString());
        Log.v("Tag", "key :" + names.stream().collect(Collectors.joining(", ")));*/
        return names.stream().collect(Collectors.joining(", "));
    }

    public void setResults(List<VideosModel> results) {
        this.results = results;
    }

}
