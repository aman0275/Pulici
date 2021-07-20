package com.example.pulici;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pulici.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivityUsers extends AppCompatActivity{

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    private RecyclerView recyclerView;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_users);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Query query = FirebaseDatabase.getInstance()
                .getReference().child("Posts")
                .limitToLast(50);

        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();

        adapter = new PostAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListner(new PostAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(DataSnapshot documentSnapshot, int position) {
                DatabaseReference ref = documentSnapshot.getRef();
                String com = ref.getKey().toString();
                Toast.makeText(MainActivityUsers.this,  com, Toast.LENGTH_SHORT).show();
                createNewDialog();
            }
        });


    }

    public void createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View postPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        dialogBuilder.setView(postPopupView);
        dialog= dialogBuilder.create();
        dialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}