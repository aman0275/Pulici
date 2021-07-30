package com.example.pulici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();

            Toast.makeText(this, "Welcome "+email, Toast.LENGTH_SHORT).show();
        }


    }

    public void logoutFunction(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void viewProfile(View view) {
        startActivity(new Intent(getApplicationContext(),profile.class));
    }



    public void addComplain(View view) {
        startActivity(new Intent(getApplicationContext(),AddPost.class));
    }

    public void post(View view) {
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("userId").child(userId);
        mFirebaseDatabase.child("role").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
               final String role =  String.valueOf(Objects.requireNonNull(task.getResult()).getValue());
                progressBar.setVisibility(View.GONE);
                if(role.equals("police")){
                    startActivity(new Intent(getApplicationContext(),MainActivityUsers.class));
                }else{
                    startActivity(new Intent(getApplicationContext(),MainActivityUsers2.class));
                }
            }
        });
    }


}