package com.example.steve.myfirstapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.steve.myfirstapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tags)
    protected TextView status;
    @BindView(R.id.user)
    protected TextView start;
    @BindView(R.id.image)
    protected ImageView image;

    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}