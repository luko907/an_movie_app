package com.lucaskoch.movieapp.model;

import androidx.annotation.Nullable;

public class CastModel implements Comparable<CastModel> {
    String known_for_department;
    int id;
    double popularity;
    String name;
    String profile_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getknown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public int compareTo(CastModel o) {
        if (this.popularity < o.popularity)
            return 1;
        else if (o.popularity < this.popularity)
            return -1;
        return 0;
    }


    @Override
    public String toString() {
        return "CastModel{" +
                "known_for_department='" + known_for_department + '\'' +
                ", id=" + id +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                ", profile_path='" + profile_path + '\'' +
                '}';
    }
}
