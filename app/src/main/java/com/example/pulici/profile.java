package com.example.pulici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
TextView pname, pemail , prole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pname = findViewById(R.id.name);
        pemail = findViewById(R.id.email);
        prole = findViewById(R.id.role);


        FirebaseUser fbUser =FirebaseAuth.getInstance().getCurrentUser();
        String profileEmail = fbUser.getEmail();
        pemail.setText(profileEmail);
        String userId = fbUser.getUid();
        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("userId").child(userId);
        mFirebaseDatabase.child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                pname.setText(String.valueOf(task.getResult().getValue()));
            }
        });

        mFirebaseDatabase.child("role").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                prole.setText(String.valueOf(task.getResult().getValue()));
            }
        });


    }

    public void plogout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}