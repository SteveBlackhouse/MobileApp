package com.example.steve.myfirstapp.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.Http_Client.ApplicationEx;
import com.google.gson.Gson;

public class DetailsInteractorImpl implements DetailsInteractor {
    private Context context;
    private DetailsInteractor.OnFinishedListener onFinishedListener;

    private SharedPreferences sharedPreferences;

    public DetailsInteractorImpl(Context context, DetailsInteractor
            .OnFinishedListener onFinishedListener) {
        this.context = context;
        this.onFinishedListener = onFinishedListener;

        sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
    }

    private String getFullName(AnimeRes fighter) {
        return fighter.getAttributes().getStartDate() + " " + fighter.getAttributes().getEndDate();
    }

    @Override
    public void toggleFavorite(AnimeRes fighter) {
        if (!sharedPreferences.contains(getFullName(fighter)))
            sharedPreferences.edit().putString(getFullName(fighter), new Gson()
                    .toJson(fighter)).apply();
        else
            sharedPreferences.edit().remove(getFullName(fighter)).apply();
    }

    @Override
    public void isFavorite(AnimeRes fighter) {
        onFinishedListener.favoriteResult(sharedPreferences.contains(getFullName(fighter)));
    }

    @Override
    public void getAnime() {
        AnimeRes fighter = ApplicationEx.getInstance().getAnimeRes();
        onFinishedListener.setAnime(fighter);
    }
}