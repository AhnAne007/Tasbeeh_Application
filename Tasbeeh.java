package com.example.tasbeehapplication;

public class Tasbeeh {

    int image;
    String count;

    public Tasbeeh(int image, String count) {
        this.image = image;
        this.count = count;
    }

    public Tasbeeh(int image) {
        this.image = image;
        this.count = "0";
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
