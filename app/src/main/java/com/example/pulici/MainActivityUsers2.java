package com.example.pulici;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pulici.models.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivityUsers2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_users2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = fbUser.getUid();


        Query query = FirebaseDatabase.getInstance()
                .getReference("Posts").orderByChild("puserId").equalTo(userId);


        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();

        adapter = new PostAdapter(options);
        recyclerView.setAdapter(adapter);





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


    public void addPost(View view) {
        startActivity(new Intent(getApplicationContext(),AddPost.class));
    }
}