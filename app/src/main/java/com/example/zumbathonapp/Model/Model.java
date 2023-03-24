package com.example.zumbathonapp.Model;

public class Model {

    String FullName, Account;

    public Model() {
    }

    public Model(String fullName, String account) {
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
