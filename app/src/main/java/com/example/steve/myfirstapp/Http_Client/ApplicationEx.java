package com.example.steve.myfirstapp.Http_Client;

import android.app.Application;

import com.example.steve.myfirstapp.AnimeRes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String ROOT_URL = "https://kitsu.io/api/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AnimeAPI getApiService() {
        return getRetrofitInstance().create(AnimeAPI.class);
    }

    private static final ApplicationEx instance = new ApplicationEx();

    private AnimeRes animeRes;

    public static ApplicationEx getInstance() {
        return instance;
    }

    public AnimeRes getAnimeRes() {
        return animeRes;
    }

    public void setAnimeRes(AnimeRes animeRes) {
        this.animeRes = animeRes;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}