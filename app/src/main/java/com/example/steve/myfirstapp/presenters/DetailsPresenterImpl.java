package com.example.steve.myfirstapp.presenters;

import android.content.Context;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.models.DetailsInteractor;
import com.example.steve.myfirstapp.models.DetailsInteractorImpl;
import com.example.steve.myfirstapp.views.DetailsView;

public class DetailsPresenterImpl implements DetailsPresenter, DetailsInteractor.OnFinishedListener {
    private DetailsView view;
    private DetailsInteractor interactor;

    private Context context;

    public DetailsPresenterImpl(DetailsView view, Context context) {
        this.view = view;
        this.context = context;

        this.interactor = new DetailsInteractorImpl(context, this);
    }

    @Override
    public void toggleFavorite(AnimeRes animeRes) {
        interactor.toggleFavorite(animeRes);
    }

    @Override
    public void ifFavorite(AnimeRes animeRes) {
        interactor.isFavorite(animeRes);
    }

    @Override
    public void getAnime() {
        interactor.getAnime();
    }

    @Override
    public void favoriteResult(boolean isFavoriteNow) {
        view.isFavorite(isFavoriteNow);
    }

    @Override
    public void setAnime(AnimeRes animeRes) {
        view.setAnime(animeRes);
    }
}