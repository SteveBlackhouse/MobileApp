package com.example.steve.myfirstapp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String ROOT_URL = "https://kitsu.io/api/";
    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static AnimeAPI getApiService() {
        return getRetrofitInstance().create(AnimeAPI.class);
    }
}