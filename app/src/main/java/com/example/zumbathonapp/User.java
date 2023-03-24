package com.example.zumbathonapp;

//we need this class for the user data MODEL
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

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
