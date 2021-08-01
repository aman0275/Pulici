package com.example.pulici;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pulici.models.Notice;
import com.example.pulici.models.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NoticeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    NoticeAdapter adapter;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    TextView poptopic,popname,popcomplain,popuserid;
    Button popdone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = fbUser.getUid();


        Query query = FirebaseDatabase.getInstance()
                .getReference("Notice");


        FirebaseRecyclerOptions<Notice> options =
                new FirebaseRecyclerOptions.Builder<Notice>()
                        .setQuery(query, Notice.class)
                        .build();

        adapter = new NoticeAdapter(options);
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