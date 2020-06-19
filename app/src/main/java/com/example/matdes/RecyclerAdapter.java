package com.example.matdes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<GameDesc> games;
    private Context context;

    public RecyclerAdapter(Context context,List<GameDesc> games) {
        this.games = games;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        GameDesc game=games.get(position);
        holder.name.setText(game.title);
       //holder.image.setImageResource(game.img);
        Glide.with(context).load(game.img).into(holder.image);

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"You like "+games.get(position).title, BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"You dislike "+games.get(position).title, BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
