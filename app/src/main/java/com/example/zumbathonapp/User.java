package com.example.zumbathonapp;

//we need this class for the user data
public class User {
    String name, account;

    public User() {
    }

    public User(String name, String account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }


    public String getAccount() {
        return account;
    }
}
