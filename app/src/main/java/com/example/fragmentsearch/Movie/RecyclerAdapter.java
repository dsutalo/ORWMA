package com.example.fragmentsearch.Movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentsearch.Movie.Movie;
import com.example.fragmentsearch.Movie.MovieClickListener;
import com.example.fragmentsearch.Movie.MovieGenre;
import com.example.fragmentsearch.Movie.NameViewHolder;
import com.example.fragmentsearch.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<NameViewHolder> {

    private List<Movie> dataList = new ArrayList<>();
    private List<MovieGenre> genreList = new ArrayList<>();
    private MovieClickListener clickListener;


    public RecyclerAdapter(MovieClickListener listener){this.clickListener = listener;}




    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_name,parent,false);
        return new NameViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        holder.tvGenre.setText("");
        holder.setMovie(dataList.get(position));
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Movie> data){
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

    public List<Movie> getDataList() {
        return dataList;
    }
}
