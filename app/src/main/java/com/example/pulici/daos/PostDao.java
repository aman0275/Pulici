package com.example.pulici.daos;

import com.example.pulici.models.Post;
import com.example.pulici.models.user;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostDao {

    private DatabaseReference databaseReference;

    public PostDao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Posts");

    }
    public Task<Void> add(Post post){
        return databaseReference.push().setValue(post);
    }
}
