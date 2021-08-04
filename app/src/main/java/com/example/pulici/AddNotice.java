package com.example.pulici;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pulici.daos.NoticeDao;
import com.example.pulici.daos.PostDao;
import com.example.pulici.models.Notice;
import com.example.pulici.models.Post;

public class AddNotice extends AppCompatActivity {

    EditText  ntopic , ndesc;
    Button addnotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        ntopic = findViewById(R.id.ntopic);
        ndesc = findViewById(R.id.ndesc);
        addnotice = findViewById(R.id.addnotice);

        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nntopic =ntopic.getText().toString().trim();
                String nndesc =ndesc.getText().toString().trim();



                NoticeDao noticedao = new NoticeDao();

                Notice notice = new Notice(nntopic,nndesc);
                noticedao.add(notice).addOnSuccessListener(suc ->{
                    Toast.makeText(AddNotice.this, "Notice Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }).addOnFailureListener(er->{

                });


            }
        });


    }

}