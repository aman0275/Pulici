package com.example.pulici;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulici.models.Post;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivityUsers2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    PostAdapter adapter;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    TextView poptopic,popname,popcomplain,popuserid;
    Button popdone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_users2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = fbUser.getUid();


        Query query = FirebaseDatabase.getInstance()
                .getReference("Posts").orderByChild("puserId").equalTo(userId);


        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();

        adapter = new PostAdapter(options);
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListner(new PostAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(DataSnapshot documentSnapshot, int position) {
                DatabaseReference ref = documentSnapshot.getRef();
                String com = ref.getKey().toString();
//                Toast.makeText(MainActivityUsers.this,  com, Toast.LENGTH_SHORT).show();
                createNewDialog(com);
            }
        });



    }


    public void createNewDialog(String com){
        dialogBuilder = new AlertDialog.Builder(this);
        final View postPopupView = getLayoutInflater().inflate(R.layout.popup, null);

        poptopic= postPopupView.findViewById(R.id.poptopic);
        popname=postPopupView.findViewById(R.id.popname);
        popcomplain = postPopupView.findViewById(R.id.popcomplain);
        popuserid = postPopupView.findViewById(R.id.popuserid);
        popdone = postPopupView.findViewById(R.id.done);


        DatabaseReference mFirebaseDatabase= FirebaseDatabase.getInstance().getReference("Posts").child(com);
        mFirebaseDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        String name =  dataSnapshot.child("name").getValue().toString();
                        String topic =  dataSnapshot.child("topic").getValue().toString();
                        String complain =  dataSnapshot.child("complain").getValue().toString();
                        String cuserid =  dataSnapshot.child("cuserId").getValue().toString();
                        String status =  dataSnapshot.child("status").getValue().toString();
                        popname.setText(name);
                        poptopic.setText(topic);
                        popcomplain.setText(complain);
                        popuserid.setText(cuserid);
                        if(status.equals("1")){
                            popdone.setText("details");
                        }else{
                            popdone.setEnabled(false);
                            popdone.setText("Pending");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                        Toast.makeText(MainActivityUsers2.this, "Database error", Toast.LENGTH_SHORT).show();
                    }

                });

        popdone.setOnClickListener(new View.OnClickListener() {
            String email,topic,complain;

            @Override
            public void onClick(View v) {
                mFirebaseDatabase.child("cuserId").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                       email = String.valueOf(task.getResult().getValue());
                    }
                });

                mFirebaseDatabase.child("topic").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                        topic = String.valueOf(task.getResult().getValue());
                    }
                });
                mFirebaseDatabase.child("complain").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                        complain = String.valueOf(task.getResult().getValue());
                    }
                });


                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"));
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, topic);
                i.putExtra(Intent.EXTRA_TEXT   , complain);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivityUsers2.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }




            }
        });


        dialogBuilder.setView(postPopupView);
        dialog= dialogBuilder.create();
        dialog.show();



    }









    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public void addPost(View view) {
        startActivity(new Intent(getApplicationContext(),AddPost.class));
    }
}