package com.example.steve.myfirstapp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steve.myfirstapp.Activity.MainActivity;
import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsFragment extends Fragment {
    public static final String FAVOURITE = "Favourite";
    private static final String DETAILS = "details__fragment";
    private boolean isImageFitToScreen;
    private SharedPreferences sharedPreferences;
    public AnimeRes animeRes;

    @BindView(R.id.image_details)
    protected ImageView imageDetails;
    @BindView(R.id.fav)
    protected ImageView favourite;
    @BindView(R.id.status)
    protected TextView status;
    @BindView(R.id.start)
    protected TextView start;
    private Bundle bundle;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        ButterKnife.bind(this, view);
        bundle = this.getArguments();
        showChildren(getAnime());
        checkFavorite();
        return view;
    }

    public AnimeRes getAnime() {
        return bundle == null ? null : new Gson()
                .fromJson(bundle.getString(DETAILS), AnimeRes.class);
    }

    private void showChildren(AnimeRes animeRes) {
        Picasso.get().load(animeRes.getAttributes()
                .getCoverImage().getLarge()).into(imageDetails);
        start.setText(animeRes.getAttributes().getStatus());
        status.setText(animeRes.getAttributes().getEndDate());
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITE, Context.MODE_PRIVATE);
    }

    @OnClick(R.id.image_details)
    void fullScreenImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            imageDetails.setAdjustViewBounds(true);
        } else {
            isImageFitToScreen = true;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            imageDetails.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.fav)
    void setFavorite() {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        if (checkFavorite()) {
            prefEditor.remove(animeRes.getAttributes().getStatus());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(animeRes);
            prefEditor.putString(animeRes.getAttributes().getStatus(), json);
            prefEditor.apply();
        }
        checkFavorite();
    }

    boolean checkFavorite() {
        Log.e("ERROR-----------------:", animeRes.getAttributes().getStatus());
        if (!sharedPreferences.contains(animeRes.getAttributes().getStatus())) {
            favourite.setImageResource(R.drawable.ic_favorite);
            return false;
        } else {
            favourite.setImageResource(R.drawable.ic_fav_black);
            return true;
        }
    }
}