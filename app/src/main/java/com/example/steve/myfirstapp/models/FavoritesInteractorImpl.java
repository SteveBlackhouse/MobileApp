package com.example.steve.myfirstapp.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.steve.myfirstapp.AnimeRes;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class FavoritesInteractorImpl implements FavoritesInteractor {
    private Context context;
    private FavoritesInteractor.OnFinishedListener onFinishedListener;

    public  FavoritesInteractorImpl(Context context, FavoritesInteractor
            .OnFinishedListener onFinishedListener) {
        this.context = context;
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    public void getAnime() {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("favorites", Context.MODE_PRIVATE);

        ArrayList<AnimeRes> fighters = new ArrayList<>();
        Map<String, ?> map = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            AnimeRes fighter = new Gson().fromJson(entry.getValue().toString(), AnimeRes.class);
            fighters.add(fighter);
        }

        onFinishedListener.addData(fighters);
    }
}