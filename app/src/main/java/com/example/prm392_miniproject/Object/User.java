package com.example.prm392_miniproject.Object;

public class User {
    String UserName;
    String Password;
    int MoneyInWallet;

    public User() {
    }

    public User(String userName, String password, int moneyInWallet) {
        UserName = userName;
        Password = password;
        MoneyInWallet = moneyInWallet;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getMoneyInWallet() {
        return MoneyInWallet;
    }

    public void setMoneyInWallet(int moneyInWallet) {
        MoneyInWallet = moneyInWallet;
    }
}
