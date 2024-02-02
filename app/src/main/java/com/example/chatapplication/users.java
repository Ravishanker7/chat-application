package com.example.chatapplication;

public class users {
    String imageUri,emaill,name,passs,id,lastmessage;

    public users(){

    }
    public users(String imageUri, String emaill, String name, String passs, String id, String lastmessage) {
        this.imageUri=imageUri;
        this.emaill=emaill;
        this.name=name;
        this.passs=passs;
        this.id=id;
        this.lastmessage=lastmessage;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasss() {
        return passs;
    }

    public void setPasss(String passs) {
        this.passs = passs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }
}