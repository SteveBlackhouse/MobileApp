package com.example.steve.myfirstapp.views;

import com.example.steve.myfirstapp.AnimeRes;

import java.util.ArrayList;

public interface ListView {
    void updateList(ArrayList<AnimeRes> animeRes);
    void onResponseFailure(Throwable t);
}
