package com.example.pulici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText fullName,email,password,passwordCheck;
    Button register;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    TextView transferToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordCheck = findViewById(R.id.passwordCheck);
        register = findViewById(R.id.register);
        transferToLogin = findViewById(R.id.transferToLogin);

        fauth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        if(fauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


    register.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String memail = email.getText().toString().trim();
            String password1 = password.getText().toString().trim();
            String password2 = passwordCheck.getText().toString().trim();
            String name = fullName.getText().toString();

            if(TextUtils.isEmpty(memail) || TextUtils.isEmpty(password1)){
                Toast.makeText(Register.this, "Fields Required", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password1.length()<6){
                Toast.makeText(Register.this, "Password too short", Toast.LENGTH_SHORT).show();
                return;
            }

//            if(password1!=password2){
//                Toast.makeText(Register.this, "Password not matched, Try with one Eye", Toast.LENGTH_SHORT).show();
//                return;
//            }


            progressBar.setVisibility(View.VISIBLE);




            fauth.createUserWithEmailAndPassword(memail,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "You Are Created In Database", Toast.LENGTH_SHORT).show();
//                        FirebaseUser user = fauth.getCurrentUser();
                        FirebaseUser fbUser =FirebaseAuth.getInstance().getCurrentUser();
                        String userId = fbUser.getUid();
//                        String emailId = fbUser.getUid();
                        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("userId").child(userId);
                        mFirebaseDatabase.child("name").setValue(name);
//                        mFirebaseDatabase.child("users").child(userId).child("fullName").setValue(name);
                        startActivity(new Intent(getApplicationContext(),SelectRole.class));
                    }else{
                        Toast.makeText(Register.this, "Error in creating you: "+ task.getException().getMessage()+" ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        }
    });

        transferToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}















