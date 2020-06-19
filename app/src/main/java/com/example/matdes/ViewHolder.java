package com.example.matdes;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView name;
    public AppCompatButton like,dislike;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        image=itemView.findViewById(R.id.imgView);
        name=itemView.findViewById(R.id.title);
        like=itemView.findViewById(R.id.like_btn);
        dislike=itemView.findViewById(R.id.dislike_btn);
    }
}
