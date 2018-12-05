package com.example.steve.myfirstapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.steve.myfirstapp.Activity.MainActivity;
import com.example.steve.myfirstapp.Adapter.AnimeAdapter;
import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.AnimeResList;
import com.example.steve.myfirstapp.Http_Client.ApplicationEx;
import com.example.steve.myfirstapp.R;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private boolean isChange = false;
    @BindView(R.id.list_photos)
    protected RecyclerView recyclerView;
    public ImageView noData;
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeContainer;
    @BindView(R.id.move)
    Button moveToFav;
    AnimeAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isChange = true;
                    makeCall();
                    swipeContainer.setRefreshing(false);
                }
            });
        }

        moveToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) view.getContext())
                        .setFragment(new FavouriteFragment(), true);
            }
        });

        makeCall();
        return view;
    }

    public void makeCall() {
        Call <AnimeResList> call = ((ApplicationEx) Objects.requireNonNull(getActivity())
                .getApplication()).getApiService().getAnimeResData();
        call.enqueue(new Callback <AnimeResList>() {
            @Override
            public void onResponse(Call <AnimeResList> call, Response <AnimeResList> response) {
                Toast.makeText(getActivity(), R.string.successful_response,
                        Toast.LENGTH_LONG).show();
                if (response.body() == null) {
                    noData.setImageAlpha(R.drawable.ic_error);
                } else {
                    ArrayList <AnimeRes> animeRes = response.body().getAnimeResList();
                    Toast.makeText(getContext(), animeRes.toString(), Toast.LENGTH_LONG);
                    if (!isChange) {
                        setAdapter(animeRes);
                    } else {
                        refreshData(animeRes);
                    }
                }
            }

            @Override
            public void onFailure(Call <AnimeResList> call, Throwable throwable) {
                Toast.makeText(getActivity(), R.string.unsuccessful_response
                        + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setAdapter(ArrayList <AnimeRes> animeRes) {
        adapter = new AnimeAdapter(getContext(), animeRes);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void refreshData(ArrayList <AnimeRes> data) {
        adapter.clear();
        adapter.loadData(data);
        swipeContainer.setRefreshing(false);
    }
}