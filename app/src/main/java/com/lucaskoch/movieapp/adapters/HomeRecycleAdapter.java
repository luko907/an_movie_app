package com.lucaskoch.movieapp.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.lucaskoch.movieapp.R;
import com.lucaskoch.movieapp.listeners.OnMovieClickListener;
import com.lucaskoch.movieapp.model.SearchArrayObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<HomeViewHolder> {


    List<SearchArrayObject> list;
    OnMovieClickListener listener;

    public HomeRecycleAdapter(/*Context context, */List<SearchArrayObject> list, OnMovieClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movies_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.textView_movie.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getPoster_path()).into(holder.imageView_poster);

        holder.home_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClick(list.get(holder.getAdapterPosition()).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class HomeViewHolder extends RecyclerView.ViewHolder {


    ImageView imageView_poster;
    TextView textView_movie;
    CardView home_container;


    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.imageView_poster);
        textView_movie = itemView.findViewById(R.id.textView_movie);
        home_container = itemView.findViewById(R.id.home_container);
    }
}