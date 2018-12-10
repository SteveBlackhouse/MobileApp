package com.example.steve.myfirstapp.models;

import com.example.steve.myfirstapp.AnimeResList;
import com.example.steve.myfirstapp.Http_Client.ApplicationEx;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListInteractorImpl implements ListInteractor {
    @Override
    public void getAnimeList(final OnFinishedListener onFinishedListener) {
        Call<AnimeResList> call = ApplicationEx.getApiService().getAnimeResData();
        call.enqueue(new Callback<AnimeResList>() {
            @Override
            public void onResponse(Call<AnimeResList> call, Response<AnimeResList> response) {
                onFinishedListener.onFinished(response.body().getAnimeResList());
            }

            @Override
            public void onFailure(Call<AnimeResList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
