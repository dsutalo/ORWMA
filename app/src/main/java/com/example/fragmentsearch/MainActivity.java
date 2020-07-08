package com.example.fragmentsearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsearch.Movie.MovieClickListener;
import com.example.fragmentsearch.Movie.MovieListResponse;
import com.example.fragmentsearch.Movie.NetworkUtils;
import com.example.fragmentsearch.Movie.RecyclerAdapter;
import com.example.fragmentsearch.User.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MovieClickListener {

    private RecyclerView recycler;
    private RecyclerAdapter adapter;




    private ImageButton ibSearch;

    private EditText etEnterMovie;
    private TextView tvPopular;
    public TextView tvTitle, tvGenre, tvDescription, tvRating;

    private String apiKey = "558b585d28e0d9a324c21db03dc55684";
    private Call<MovieListResponse> apiCall;
    private Call<MovieListResponse> apiCallPopular;

    private static final String TAG = "MainActivity";
    private static final String KEY_TITLE = "title";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_RATING = "rating";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String userID;
    public static String movieID;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
       // ibDelete.setVisibility(View.INVISIBLE);
        setupRecycler();
        fetchPopularMovies();
        setupListeners();




    }

    private void setupUI() {
        ibSearch = findViewById(R.id.ibSearch);
        etEnterMovie = findViewById(R.id.etSearch);
        tvPopular = findViewById(R.id.tvPopular);
        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser().getUid());



        tvRating = findViewById(R.id.tvRating1);
        tvDescription = findViewById(R.id.tvDescription1);



    }

    private void setupRecycler(){
        recycler = findViewById(R.id.recycleView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter(this);
        recycler.setAdapter(adapter);
    }



    private void setupListeners(){
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchMovies();
                tvPopular.setAlpha(0f);
            }
        });
    }

    private void fetchMovies() {
        apiCall = NetworkUtils.getApiInterface().getMovies(apiKey, etEnterMovie.getText().toString());
        apiCall.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(!response.isSuccessful()){
                    tvPopular.setText("Code" + response.code());
                    return;
                }
                adapter.addData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchPopularMovies(){
        apiCallPopular = NetworkUtils.getApiInterface().getPopular(apiKey,"en-US", 1);
        apiCallPopular.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(!response.isSuccessful()){
                    tvPopular.setText("Code" +response.code());
                }
                adapter.addData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(apiCall != null){
            apiCall.cancel();
        }
    }

    @Override
    public void onFavoritesClickListener( int position) {


        Map<String,Object> movie = new HashMap<>();
        movie.put(KEY_TITLE,adapter.getDataList().get(position).getName());
        movie.put(KEY_RATING,adapter.getDataList().get(position).getRating());
        movie.put(KEY_DESCRIPTION,adapter.getDataList().get(position).getDescription());
        movie.put(KEY_IMAGE, adapter.getDataList().get(position).getImage_link());

        movieID = adapter.getDataList().get(position).getMovieID().toString();

        firebaseFirestore.collection("users").document(userID).collection("FavoriteMovies").document(movieID).set(movie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Movie added to Favorites", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });



    }




    public void skokU(View view) {
        startActivity(new Intent(getApplicationContext(), User.class));
    }


}
