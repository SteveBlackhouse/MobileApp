package com.example.steve.myfirstapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeAPI {
    @GET("edge/anime/")
    Call<AnimeResList> getAnimeResData();
}