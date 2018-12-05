package com.example.steve.myfirstapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steve.myfirstapp.Activity.MainActivity;
import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.Attributes;
import com.example.steve.myfirstapp.R;
import com.example.steve.myfirstapp.fragments.DetailsFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter <ViewHolder> {

    private static final String DETAILS = "details__fragment";
    private ArrayList <AnimeRes> anime;
    private final Context context;

    public AnimeAdapter(Context context, ArrayList <AnimeRes> animeRes) {
        this.context = context;
        anime = animeRes;
    }

    public void loadData(ArrayList <AnimeRes> animeRes) {
        int position = getItemCount();
        this.anime.addAll(animeRes);
        notifyItemRangeInserted(position, this.anime.size());
    }

    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Attributes attributes = anime.get(position).getAttributes();
        Picasso.get().load(attributes.getCoverImage().getLarge()).into(holder.image);
        holder.status.setText(attributes.getStatus());
        holder.start.setText(attributes.getStartDate());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsFragment fragment = new DetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(DETAILS, new Gson().toJson(anime.get(position)));
                fragment.setArguments(bundle);
                ((MainActivity) view.getContext()).setFragment(fragment, true);
            }
        });
    }


    public void clear() {
        anime.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return anime.size();
    }

    public void setItems(ArrayList <AnimeRes> photos) {
        this.anime = photos;
    }

}