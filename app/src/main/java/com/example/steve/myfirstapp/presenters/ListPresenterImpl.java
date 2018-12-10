package com.example.steve.myfirstapp.presenters;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.fragments.ListFragment;
import com.example.steve.myfirstapp.models.ListInteractor;
import com.example.steve.myfirstapp.models.ListInteractorImpl;

import java.util.ArrayList;

public class ListPresenterImpl implements ListPresenter, ListInteractor.OnFinishedListener {
    private ListFragment view;
    private ListInteractor interactor;

    public ListPresenterImpl(ListFragment listView) {
        this.view = listView;
        this.interactor = new ListInteractorImpl();
    }

    @Override
    public void onFinished(ArrayList <AnimeRes> animeRes) {

        if (view != null)
            view.updateList(animeRes);
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null)
            view.onResponseFailure(t);

    }

    @Override
    public void requestData() {
        interactor.getAnimeList(this);
    }
}