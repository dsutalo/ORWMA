package com.example.fragmentsearch.Movie;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameViewHolder extends RecyclerView.ViewHolder {

    private static final String API_KEY = "558b585d28e0d9a324c21db03dc55684";
    private static final String MOVIE_LANGUAGE = "en-us";

    private ImageView ivPicture;
    private TextView tvName;
    private TextView tvRating;
    private TextView tvDescription;
    private ImageView ivFavorites;
    public TextView tvGenre;




    public NameViewHolder(@NonNull View itemView, final MovieClickListener clickListener) {
        super(itemView);
        setupUI();

        ivFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onFavoritesClickListener(getAdapterPosition());

            }
        });





    }

    private void setupUI() {
        ivPicture = itemView.findViewById(R.id.ivPicture);
        tvName = itemView.findViewById(R.id.tvName);
        tvRating = itemView.findViewById(R.id.tvRating);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        ivFavorites = itemView.findViewById(R.id.ivFavorites);

        tvGenre = itemView.findViewById(R.id.tvGenre);
    }

    public void setMovie(Movie movie){
        tvName.setText(movie.getName());
        tvRating.setText(movie.getRating());
        tvDescription.setText(movie.getDescription());
        Picasso.get().load("https://image.tmdb.org/t/p/original/" + movie.getImage_link()).into(ivPicture);


        fetchMovieGenre(movie);

    }

    public void fetchMovieGenre(final Movie movie){

        Call<MovieGenreList> apiCall = NetworkUtils.getApiInterface().getGenre(movie.getMovieID(),API_KEY,MOVIE_LANGUAGE );
        apiCall.enqueue(new Callback<MovieGenreList>() {
            @Override
            public void onResponse(Call<MovieGenreList> call, Response<MovieGenreList> response) {
                if(!response.isSuccessful()){
                    tvGenre.setText("error");
                }
                List<MovieGenre> genres = response.body().getGenres();
                for(MovieGenre movieGenre : genres){
                    tvGenre.append(movieGenre.getName() + " ");
                }
            }

            @Override
            public void onFailure(Call<MovieGenreList> call, Throwable t) {

            }
        });
    }






}
