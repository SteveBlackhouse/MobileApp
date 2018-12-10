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
import com.example.steve.myfirstapp.Http_Client.ApplicationEx;
import com.example.steve.myfirstapp.R;
import com.example.steve.myfirstapp.fragments.DetailsFragment;
import com.google.gson.Gson;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter <ViewHolder> {
    private static final String DETAILS = "details__fragment";
    private ArrayList <AnimeRes> anime;
    private ArrayList<AnimeRes> animeRes;
    private Context context;

    public AnimeAdapter(Context context,ArrayList<AnimeRes> animeRes){
        this.context = context;
        this.animeRes = animeRes;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.info, parent, false);
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

    @Override
    public int getItemCount() {
        return Math.min(animeRes.size(),10);
    }

    public void updateList(ArrayList<AnimeRes> fighters) {
        this.animeRes.clear();
        this.animeRes.addAll(fighters);
        notifyDataSetChanged();
    }
}