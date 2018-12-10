package com.example.steve.myfirstapp.presenters;

import android.content.Context;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.models.FavoritesInteractor;
import com.example.steve.myfirstapp.models.FavoritesInteractorImpl;
import com.example.steve.myfirstapp.views.FavoritesView;

import java.util.ArrayList;

public class FavoritesPresenterImpl implements FavoritesPresenter, FavoritesInteractor.OnFinishedListener {
    private FavoritesView view;
    private FavoritesInteractor interactor;
    private Context context;

    public FavoritesPresenterImpl(FavoritesView view, Context context) {
        this.view = view;
        this.context = context;
        this.interactor = new FavoritesInteractorImpl(context, this);
    }

    @Override
    public void requestData() {
        interactor.getAnime();
    }

    @Override
    public void addData(ArrayList<AnimeRes> animeRes) {
        view.setData(animeRes);
    }
}