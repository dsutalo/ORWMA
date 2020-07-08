package com.example.fragmentsearch.Movie;

import com.google.gson.annotations.SerializedName;

public class MovieGenre {
    @SerializedName("name")
    private String genreName;

    public String getName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
