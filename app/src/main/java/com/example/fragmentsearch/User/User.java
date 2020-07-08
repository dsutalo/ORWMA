package com.example.fragmentsearch.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragmentsearch.Movie.Movie;
import com.example.fragmentsearch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

public class User extends AppCompatActivity {

    TextView fullName, email, phone, tvFavoritesEmpty;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;

    List<Movie> movieList = new ArrayList<>();



    private RecyclerView favoritesRecycler;
    private FavoritesRecyclerAdapter favoritesAdapter;

    private static final String KEY_TITLE = "title";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_RATING = "rating";
    private static final String KEY_DESCRIPTION = "description";

    private TextView tvTitle, tvGenre, tvDescription, tvRating;
    private String TAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setupUI();
        setupRecycler();


        movieList = new ArrayList<>();
        loadDataFromFirestore();





        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                phone.setText(documentSnapshot != null ? documentSnapshot.getString("phone"): null);
                fullName.setText(documentSnapshot != null ? documentSnapshot.getString("fullName"): null);
                email.setText(documentSnapshot != null ? documentSnapshot.getString("email"): null);

            }
        });



    }



    private void loadDataFromFirestore() {
        firebaseFirestore.collection("users").document(userID).collection("FavoriteMovies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()){
                            Movie movie = new Movie(querySnapshot.getString("title"),
                                                    querySnapshot.getString("rating"),
                                                    querySnapshot.getString("description"),
                                                    querySnapshot.getString("image"));
                            movieList.add(movie);
                            tvFavoritesEmpty.setAlpha(0f);

                        }
                        favoritesAdapter = new FavoritesRecyclerAdapter(movieList);
                        favoritesRecycler.setAdapter(favoritesAdapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(User.this, "error load", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    private void setupRecycler() {
        favoritesRecycler = findViewById(R.id.recycleView1);
        favoritesRecycler.setHasFixedSize(true);
        favoritesRecycler.setLayoutManager(new LinearLayoutManager(this));





    }

    private void setupUI() {
        fullName = findViewById(R.id.tvUserName);
        email = findViewById(R.id.tvUserEmail);
        phone = findViewById(R.id.tvUSerPhone);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser().getUid());

        tvTitle = findViewById(R.id.tvName1);
        tvRating = findViewById(R.id.tvRating1);
        tvDescription = findViewById(R.id.tvDescription1);
        tvFavoritesEmpty = findViewById(R.id.tvFavoritesEmpty);



    }





    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }






}