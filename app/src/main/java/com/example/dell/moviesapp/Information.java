package com.example.dell.moviesapp;


public class Information {
    String title;
    String image;
    public Information(String title,String image){
        this.image=image;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
