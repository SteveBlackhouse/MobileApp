package com.example.steve.myfirstapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.steve.myfirstapp.AnimeRes;
import com.example.steve.myfirstapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter <ViewHolder> {
    private ArrayList <AnimeRes> anime;

    public AnimeAdapter(ArrayList <AnimeRes> animeRes) {
        anime = animeRes;
    }

    @Override
    public final ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(anime.get(position).getAttributes()
                .getCoverImage().getLarge()).into(holder.image);
        holder.status.setText(anime.get(position).getAttributes().getStatus());
        holder.start.setText(anime.get(position).getAttributes().getStartDate());
    }

    @Override
    public int getItemCount() {
        return anime.size();
    }
}