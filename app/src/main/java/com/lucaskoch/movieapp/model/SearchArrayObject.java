package com.lucaskoch.movieapp.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchArrayObject {
    String title;
    String poster_path;
    String release_date;
    int id;
    int vote_count;
    String overview;
    String backdrop_path;
    int runtime;
    double vote_average;
    List<Companies> production_companies;
    List<Genres> genres;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getGenres() {
        List<Genres> filteredGenres = new ArrayList<>(genres);
        List<String> names = new ArrayList<>();

        for (Genres obj: filteredGenres) {
            names.add(obj.getName());
        }
        return names.stream().collect(Collectors.joining(", "));
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getProduction_companies() {

        List<Companies> filteredCompanies = production_companies.stream().filter(element -> element.getLogo_path() != null).sorted().limit(2).collect(Collectors.toList());
        List<String> names = new ArrayList<>();

        for (Companies obj: filteredCompanies) {
            names.add(obj.getName());
        }
        return names.stream().collect(Collectors.joining(", "));
    }

    public void setProduction_companies(List<Companies> production_companies) {
        this.production_companies = production_companies;
    }

    public String getVote_average() {
        return String.valueOf(vote_average);
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public double getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500" + poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date.substring(0, 4);
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return "https://image.tmdb.org/t/p/w500" + backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public String toString() {
        return "SearchArrayObject{" +
                "title='" + title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", id=" + id +
                ", vote_count=" + vote_count +
                ", overview='" + overview + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", runtime=" + runtime +
                '}';
    }
}
