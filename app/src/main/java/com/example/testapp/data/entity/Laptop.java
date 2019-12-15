package com.example.testapp.data.entity;

public class Laptop {

    String title;
    String description;
    String image;

    public Laptop(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.image = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }
}
