package com.lucaskoch.movieapp.model;

public class CrewModel implements Comparable<CrewModel>{

    String job;
    int id;
    double popularity;
    String name;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int compareTo(CrewModel o) {
        if (this.popularity < o.popularity)
            return 1;
        else if (o.popularity < this.popularity)
            return -1;
        return 0;
    }
    @Override
    public String toString() {
        return "CrewModel{" +
                "job='" + job + '\'' +
                ", id=" + id +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                '}';
    }
}
