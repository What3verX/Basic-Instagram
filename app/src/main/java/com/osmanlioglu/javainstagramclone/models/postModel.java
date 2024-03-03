package com.osmanlioglu.javainstagramclone.models;

import com.google.firebase.Timestamp;

public class postModel {

    public String explaination;
    public String email;
    public String downloadUrl;
    public Timestamp timestamp;




    public postModel(String explaination, String email, String downloadUrl, Timestamp timestamp) {
        this.explaination = explaination;
        this.email = email;
        this.downloadUrl = downloadUrl;
        this.timestamp = timestamp;
    }
}
