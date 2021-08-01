package com.example.pulici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pulici.daos.PostDao;
import com.example.pulici.models.Post;

public class AddPost extends AppCompatActivity {

    EditText cname, ctopic , ccomplain;
    Button addcomplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        cname = findViewById(R.id.cname);
        ctopic = findViewById(R.id.ntopic);
        ccomplain = findViewById(R.id.ndesc);
        addcomplain = findViewById(R.id.addnotice);

        addcomplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tcname =cname.getText().toString().trim();
                String tctopic =ctopic.getText().toString().trim();
                String tccomplain =ccomplain.getText().toString().trim();

                if(tcname.isEmpty() || tctopic.isEmpty() || tccomplain.isEmpty() ){
                    Toast.makeText(AddPost.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                    return;
                }

                PostDao postdao = new PostDao();

                Post post = new Post(tcname,tctopic,tccomplain);
                postdao.add(post).addOnSuccessListener(suc ->{
                    Toast.makeText(AddPost.this, "Complain Filed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }).addOnFailureListener(er->{
                    Toast.makeText(AddPost.this, "Error uploading - "+ er.getMessage(), Toast.LENGTH_SHORT).show();
                });


            }
        });
    }
}