package com.example.fragmentsearch.User;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsearch.Movie.Movie;
import com.example.fragmentsearch.Movie.MovieGenre;
import com.example.fragmentsearch.Movie.MovieGenreList;
import com.example.fragmentsearch.Movie.NetworkUtils;
import com.example.fragmentsearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvRating;
    private TextView tvDescription;
    private ImageView ivFavorites;
    public TextView tvGenre;

    public FavoritesViewHolder(@NonNull View itemView) {
        super(itemView);
        setupUI();

        /*ivFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClickListener(getAdapterPosition());
            }
        });
*/
    }

    private void setupUI() {
        ivPicture = itemView.findViewById(R.id.ivPicture1);
        tvName = itemView.findViewById(R.id.tvName1);
        tvRating = itemView.findViewById(R.id.tvRating1);
        tvDescription = itemView.findViewById(R.id.tvDescription1);



    }

    public void setMovie(Movie movie){
        tvName.setText(movie.getName());
        tvRating.setText(movie.getRating());
        tvDescription.setText(movie.getDescription());
        Picasso.get().load("https://image.tmdb.org/t/p/original/" + movie.getImage_link()).into(ivPicture);



    }




}
