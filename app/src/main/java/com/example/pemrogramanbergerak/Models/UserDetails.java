package com.example.pemrogramanbergerak.Models;

public class UserDetails {
    public String uid, username, email, password, nim;

    public UserDetails() {
        // Diperlukan untuk Firebase
    }

    public UserDetails(String uid, String username, String email, String password, String nim) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nim = nim;
    }
}
