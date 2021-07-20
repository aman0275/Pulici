package com.example.pulici.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

public class Post {
      String name;
      String topic;
      String complain;
     String puserId;
     String cuserId;
     int status;

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public  String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getPuserId() {
        return puserId;
    }

    public void setPuserId(String puserId) {
        this.puserId = puserId;
    }

    public String getCuserId() {
        return cuserId;
    }

    public void setCuserId(String cuserId) {
        this.cuserId = cuserId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    Date date = new Date();
    private String cdate;

    FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();

    public Post(){

    }

    public Post(String name, String topic, String complain ) {
        this.name = name;
        this.topic = topic;
        this.complain = complain;
        this.puserId = fbUser.getUid();
        this.status = 0;
        cdate = date.toString();
        cuserId = "Pending";

    }
}
