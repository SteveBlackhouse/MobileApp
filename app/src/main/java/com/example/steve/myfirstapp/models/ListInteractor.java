package com.example.steve.myfirstapp.models;

import com.example.steve.myfirstapp.AnimeRes;

import java.util.ArrayList;


public interface ListInteractor {
    interface OnFinishedListener {
        void onFinished(ArrayList<AnimeRes> animeRes);

        void onFailure(Throwable t);
    }

    void getAnimeList(OnFinishedListener onFinishedListener);
}