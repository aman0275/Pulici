package com.example.pulici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();

            // Check if user's email is verified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();


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

    public void allPost(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivityUsers.class));
    }

    public void allPostUser(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivityUsers2.class));
    }


}