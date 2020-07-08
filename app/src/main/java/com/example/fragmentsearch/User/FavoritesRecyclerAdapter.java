package com.example.fragmentsearch.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.fragmentsearch.Movie.Movie;
import com.example.fragmentsearch.R;

public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {


    private List<Movie> favoritesList;


    FavoritesRecyclerAdapter() {
    }

    FavoritesRecyclerAdapter(List<Movie> favoritesList) {
        this.favoritesList = favoritesList;
    }


    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_cell, parent, false);
        return new FavoritesViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {

        holder.setMovie(favoritesList.get(position));

    }

    @Override
    public int getItemCount() {
        return favoritesList.size();
    }


}
