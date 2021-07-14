package com.example.pulici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectRole extends AppCompatActivity {
TextView username;
Button police,users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role);

        police = findViewById(R.id.police);
        users = findViewById(R.id.users);

        username = findViewById(R.id.username);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        username.setText(email);

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser fbUser =FirebaseAuth.getInstance().getCurrentUser();
                String userId = fbUser.getUid();
                DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("userId").child(userId);
                mFirebaseDatabase.child("role").setValue("police");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser fbUser =FirebaseAuth.getInstance().getCurrentUser();
                String userId = fbUser.getUid();
                DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("userId").child(userId);
                mFirebaseDatabase.child("role").setValue("user");
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }


}


