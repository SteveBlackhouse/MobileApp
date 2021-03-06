package com.example.steve.myfirstapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnimeRes {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("canonicalTitle")
    @Expose
    private String canonicalTitle;

    @SerializedName("type")
    @Expose
    private String type;

    @Override
    public String toString() {
        return "AnimeRes{" +
                "id='" + id + '\'' +
                ", canonicalTitle='" + canonicalTitle + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }
}