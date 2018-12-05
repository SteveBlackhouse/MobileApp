package com.example.steve.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeCall();
    }
  
    public void makeCall() {
        Call<AnimeResList> call = ApplicationEx.getApiService().getAnimeResData();
        call.clone().enqueue(new Callback<AnimeResList>() {
            @Override
            public void onResponse(Call <AnimeResList> call, Response<AnimeResList> response) {
                Log.d(TAG, "onResponse: Server Response: " + response.toString());
                Log.d(TAG, "onResponse: received information: " +
                        Objects.requireNonNull(response.body()).toString());
                ArrayList<AnimeRes> animeRes = response.body().getAnimeResList();
                displayData(animeRes);
            }

            @Override
            public void onFailure(Call <AnimeResList> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }

            private void displayData(ArrayList <AnimeRes> animeRes) {
                for (int i = 0; i < animeRes.size(); i++) {
                    Log.d("Data", animeRes.get(i).toString() +
                            "\n--------------------------------------");
                }
            }
        });
    }
}
