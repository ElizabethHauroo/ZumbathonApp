package com.example.zumbathonapp;

public class MainModel {

    String FullName, Account; //the variables should be exactly as they are in Firebase

    public MainModel() {
    }

    public MainModel(String fullName, String account) {
        FullName = fullName;
        Account = account;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
