package com.example.chatapplication.Modelclass;

public class userstatusmodel {
    public String imageUri;

    public userstatusmodel() {
    }
    userstatusmodel(String imageUri){
        this.imageUri=imageUri;
    }

    public String getimageUri() {
        return imageUri;
    }

    public void setimageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
