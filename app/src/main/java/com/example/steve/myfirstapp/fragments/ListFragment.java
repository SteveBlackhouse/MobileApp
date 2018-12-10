package com.example.steve.myfirstapp.fragments;

import android.app.ProgressDialog;
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
import com.example.steve.myfirstapp.R;
import com.example.steve.myfirstapp.presenters.ListPresenter;
import com.example.steve.myfirstapp.presenters.ListPresenterImpl;
import com.example.steve.myfirstapp.views.ListView;

import java.util.ArrayList;

import butterknife.BindView;

public class ListFragment extends Fragment implements ListView {
    private ListPresenter presenter;
    private ProgressDialog progDialog;
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
        presenter = new ListPresenterImpl(this);

        progDialog = new ProgressDialog(this.getContext());
        progDialog.show();
        adapter = new AnimeAdapter(this.getContext(), new ArrayList <AnimeRes>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.requestData();
            }
        });

        moveToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) v.getContext())
                        .setFragment(new FavouriteFragment(), true);
            }
        });

        presenter.requestData();
        return view;
    }

    private void updateRefreshingUI(boolean isSuccess) {
        progDialog.dismiss();
        swipeContainer.setRefreshing(false);
        noData.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(isSuccess ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateList(ArrayList <AnimeRes> animeRes) {
        adapter.updateList(animeRes);
        updateRefreshingUI(true);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(ListFragment.this.getContext(), getString(R.string.on_failure),
                Toast.LENGTH_SHORT).show();
        updateRefreshingUI(false);
    }
}