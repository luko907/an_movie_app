package com.lucaskoch.movieapp.model;

import androidx.annotation.NonNull;

public class VideosModel {

    String id;
    String name;
    String key;
    String type;
    Boolean official;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    @NonNull
    @Override
    public String toString() {
        return "VideosModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", official='" + official + '\'' +
                '}';
    }
}
