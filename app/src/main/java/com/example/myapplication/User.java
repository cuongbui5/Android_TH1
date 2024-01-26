package com.example.myapplication;

import android.net.Uri;

import androidx.annotation.NonNull;

public class User {
    private String name;
    private String phone;
    private String gender;
    private String city;
    private Uri img;

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public User(String name, String phone, String gender, String city, Uri img) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.city = city;
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return name+" - "+gender+" - "+phone+" - "+city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
