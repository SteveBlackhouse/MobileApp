package com.example.steve.myfirstapp.models;

import com.example.steve.myfirstapp.AnimeRes;

import java.util.ArrayList;

public interface FavoritesInteractor {
    interface OnFinishedListener {
        void addData(ArrayList <AnimeRes> animeRes);
    }

    void getAnime();
}
