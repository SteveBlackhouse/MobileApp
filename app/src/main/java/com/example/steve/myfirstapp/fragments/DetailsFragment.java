package com.example.steve.myfirstapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.R;
import com.example.steve.myfirstapp.presenters.DetailsPresenter;
import com.example.steve.myfirstapp.presenters.DetailsPresenterImpl;
import com.example.steve.myfirstapp.views.DetailsView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsFragment extends Fragment implements DetailsView {
    private static final int normalSize = 300;
    private static final int bigSize = 1000;
    private DetailsPresenter presenter;
    private AnimeRes animeRes;
    private boolean isFullScreen;


    @BindView(R.id.image_details)
    protected ImageView imageDetails;
    @BindView(R.id.fav)
    protected ImageView favourite;
    @BindView(R.id.status)
    protected TextView status;
    @BindView(R.id.start)
    protected TextView start;

    @BindView(R.id.move)
    protected Button addRemoveFavorite;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        ButterKnife.bind(this, view);
        presenter = new DetailsPresenterImpl(this, getContext());
        presenter.getAnime();
        setContent();
        presenter.ifFavorite(animeRes);
        imageDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageClick();
            }
        });
        addRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleFavorite(animeRes);
                presenter.ifFavorite(animeRes);
            }
        });
        isFullScreen = false;
        return view;
    }

    private void handleImageClick() {
        if (isFullScreen) {
            imageDetails.setLayoutParams(new LinearLayout.LayoutParams(normalSize, normalSize));
        } else {
            imageDetails.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                    .MATCH_PARENT, bigSize));
        }
        isFullScreen = !isFullScreen;
    }

    private void setContent() {
        Picasso.get().load(animeRes.getAttributes().getCoverImage().getMedium()).into(imageDetails);
        start.setText(animeRes.getAttributes().getStatus());
        status.setText(animeRes.getAttributes().getEndDate());
    }

    @Override
    public void isFavorite(boolean isFavorite) {
        addRemoveFavorite.setText(isFavorite ? "Remove from favorite" : "Add to favorite");
    }

    @Override
    public void setAnime(AnimeRes fighter) {

    }
}