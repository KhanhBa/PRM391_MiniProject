package com.example.prm392_miniproject.Object;

public class Runner {
    String Name;
    int Image;

    public Runner() {
    }

    public Runner(String name, int image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
