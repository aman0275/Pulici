package com.example.pulici.daos;

import com.example.pulici.models.user;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class daos {
    private DatabaseReference databaseReference;

    public daos() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(user.class.getName());
    }
}
