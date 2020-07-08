package com.example.fragmentsearch.Movie;

import com.example.fragmentsearch.Movie.Movie;

import java.util.List;

public class MovieListResponse {

    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results;

   public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
