package com.lucaskoch.movieapp.model;

public class Companies implements Comparable<Companies>{

    int id;
    String logo_path;
    String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + id +
                ", logo_path='" + logo_path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Companies o) {
        if (this.id < o.id)
            return -1;
        else if (o.id < this.id)
            return 1;
        return 0;
    }
}
