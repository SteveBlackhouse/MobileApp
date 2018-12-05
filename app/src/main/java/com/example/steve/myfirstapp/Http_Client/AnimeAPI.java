package com.example.steve.myfirstapp.Http_Client;

import com.example.steve.myfirstapp.AnimeResList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeAPI {
    @GET("edge/anime/")
    Call<AnimeResList> getAnimeResData();
}