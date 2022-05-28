package com.example.ibooking2.object;

import java.io.Serializable;

public class Room implements Serializable {
    private String name, location, description;
    private int full_capacity, score, image_url;

    public Room(String name, String location, int image_url, int full_capacity, int score, String description) {
        this.name = name;
        this.location = location;
        this.image_url = image_url;
        this.full_capacity = full_capacity;
        this.score = score;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    public int getFull_capacity() {
        return full_capacity;
    }

    public void setFull_capacity(int full_capacity) {
        this.full_capacity = full_capacity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}