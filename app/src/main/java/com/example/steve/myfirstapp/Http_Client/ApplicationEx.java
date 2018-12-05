package com.example.steve.myfirstapp.Http_Client;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String ROOT_URL = "https://kitsu.io/api/";

    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public AnimeAPI getApiService() {
        return getRetrofitInstance().create(AnimeAPI.class);
    }
}