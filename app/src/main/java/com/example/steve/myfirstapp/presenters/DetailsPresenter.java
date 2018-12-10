package com.example.steve.myfirstapp.presenters;

import com.example.steve.myfirstapp.AnimeRes;

public interface DetailsPresenter {
    void toggleFavorite(AnimeRes animeRes);
    void ifFavorite(AnimeRes animeRes);

    void getAnime();
}
