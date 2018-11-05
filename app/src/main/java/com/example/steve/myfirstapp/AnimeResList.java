package com.example.steve.myfirstapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AnimeResList {
    @SerializedName("data")
    @Expose
    private ArrayList<AnimeRes> AnimeResList;

    ArrayList<AnimeRes> getAnimeResList() {
        return AnimeResList;
    }

    public void setAnimeResList(ArrayList<AnimeRes> AnimeResList) {
        this.AnimeResList = AnimeResList;
    }

    @Override
    public String toString() {
        return "AnimeResList{" +
                "AnimeResList=" + AnimeResList +
                '}';
    }
}