package com.example.steve.myfirstapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steve.myfirstapp.Adapter.AnimeAdapter;
import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.R;
import com.example.steve.myfirstapp.presenters.FavoritesPresenter;
import com.example.steve.myfirstapp.presenters.FavoritesPresenterImpl;
import com.example.steve.myfirstapp.views.FavoritesView;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteFragment extends Fragment implements FavoritesView {
    private AnimeAdapter adapter;
    private FavoritesPresenter presenter;
    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        presenter = new FavoritesPresenterImpl(this, getActivity());
        presenter.requestData();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void setData(ArrayList<AnimeRes> animeRes) {
        adapter = new AnimeAdapter(this.getContext(), animeRes);
        recyclerView.setAdapter(adapter);
    }
}