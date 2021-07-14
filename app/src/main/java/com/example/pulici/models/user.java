package com.example.pulici.models;

public class user {
    private String uid;
    private String name;
    private String role;
    private String imageUrl;

    public user(){}
    public user(String uid, String name, String role, String imageUrl) {
        this.uid = uid;
        this.name = name;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
