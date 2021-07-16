package com.example.pulici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.account){
            //Toast.makeText(this, "Click Search Icon.", Toast.LENGTH_SHORT).show();
            //TODO make account intent here
        }
        return super.onOptionsItemSelected(item);
    }
    
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
}