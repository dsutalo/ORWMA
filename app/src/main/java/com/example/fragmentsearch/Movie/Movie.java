package com.example.fragmentsearch.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie {


    @SerializedName("title")
    private String name;
    @SerializedName("vote_average")
    private String rating;
    @SerializedName("overview")
    private String description;
    @SerializedName("poster_path")
    private String image_link;
    @SerializedName("id")
    private Integer movieID;
    private String genre;




    public Movie(){}
    public Movie(String name,//String genre,
                 String rating,String description,String image_link){
        this.name = name;
       // this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.image_link = image_link;


    }

    public Integer getMovieID() {
        return movieID;
    }

    public String getName() {
        return name;
    }



    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }





}
