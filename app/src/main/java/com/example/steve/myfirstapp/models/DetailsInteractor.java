package com.example.steve.myfirstapp.models;

import com.example.steve.myfirstapp.AnimeRes;

public interface DetailsInteractor {
    interface OnFinishedListener {
        void favoriteResult(boolean isFavoriteNow);
        void setAnime(AnimeRes fighter);
    }

    void toggleFavorite(AnimeRes fighter);
    void isFavorite(AnimeRes fighter);

    void getAnime();
}
