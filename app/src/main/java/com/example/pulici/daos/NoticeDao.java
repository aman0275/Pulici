package com.example.pulici.daos;

import com.example.pulici.models.Notice;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeDao {
    private DatabaseReference databaseReference;

    public NoticeDao() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("Notice");

    }
    public Task<Void> add(Notice notice){
        return databaseReference.push().setValue(notice);
    }
}
