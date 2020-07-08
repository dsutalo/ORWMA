package com.example.fragmentsearch.Movie;

import com.example.fragmentsearch.Movie.MovieGenre;
import com.example.fragmentsearch.Movie.MovieGenreList;
import com.example.fragmentsearch.Movie.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("3/search/movie")
    Call<MovieListResponse> getMovies(
            @Query("api_key") String api_key,
            @Query("query") String query
    );

    @GET("3/movie/popular")
    Call<MovieListResponse> getPopular(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int number

    );

    @GET("3/movie/{movie_id}")
    Call<MovieGenreList> getGenre(
            @Path("movie_id") int movieID,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );






}
