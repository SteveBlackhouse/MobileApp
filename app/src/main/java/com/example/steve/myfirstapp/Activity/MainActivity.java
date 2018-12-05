package com.example.steve.myfirstapp.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.steve.myfirstapp.Adapter.AnimeAdapter;
import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.AnimeResList;
import com.example.steve.myfirstapp.Http_Client.ApplicationEx;
import com.example.steve.myfirstapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    protected SwipeRefreshLayout swipeContainer;
    AnimeAdapter adapter;
    public ImageView noData;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                makeCall();
                swipeContainer.setRefreshing(false);
            }
        });
        makeCall();
    }

    public void makeCall() {
        Call <AnimeResList> call = ((ApplicationEx) getApplication())
                .getApiService().getAnimeResData();
        call.clone().enqueue(new Callback <AnimeResList>() {
            @Override
            public void onResponse(Call <AnimeResList> call, Response <AnimeResList> response) {
                Toast.makeText(MainActivity.this, R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setImageAlpha(R.drawable.ic_error);
                } else {
                    ArrayList <AnimeRes> animeRes = response.body().getAnimeResList();
                    setAdapter(animeRes);
                }
            }

            @Override
            public void onFailure(Call <AnimeResList> call, Throwable t) {
                Toast.makeText(MainActivity.this, R.string.unsuccessful_response
                        + t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void setAdapter(ArrayList <AnimeRes> data) {
        adapter = new AnimeAdapter(data);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}