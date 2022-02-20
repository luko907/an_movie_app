package com.lucaskoch.movieapp.model;


import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class CastCrew {
    List<CastModel> cast = null;
    List<CrewModel> crew = null;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCast() {
        List<CastModel> filteredCast = cast.stream().filter(element -> element.getknown_for_department().equals("Acting")).sorted().limit(4).collect(Collectors.toList());
        List<String> names = new ArrayList<>();

        for (CastModel obj: filteredCast) {
            names.add(obj.getName());
        }
        return names.stream().collect(Collectors.joining(", "));
    }

    public void setCast(List<CastModel> cast) {
        this.cast = cast;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getCrew() {
        List<CrewModel> filteredCrew = crew.stream().filter(element -> element.getJob().equals("Director")).sorted().limit(1).collect(Collectors.toList());
        List<String> names = new ArrayList<>();

        for (CrewModel obj: filteredCrew) {
            names.add(obj.getName());
        }
        return names.stream().collect(Collectors.joining(", "));
    }

    public void setCrew(List<CrewModel> crew) {
        this.crew = crew;
    }
}
