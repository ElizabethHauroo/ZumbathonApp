package com.example.zumbathonapp;

//we need this class for the user data
public class User {
    String FullName, Account;

    public User() {
    }

    public User(String name, String account) {
        this.FullName = name;
        this.Account = account;
    }

    public String getFullName() {
        return FullName;
    }


    public String getAccount() {
        return Account;
    }
}
