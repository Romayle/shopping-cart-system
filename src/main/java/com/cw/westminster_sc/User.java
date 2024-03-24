package com.cw.westminster_sc;

public class User {
    private String userName;
    private String password;
    private ShoppingCart cart;

    public User(String accountName, String password) {
        this.userName = accountName;
        this.password = password;
    }

}
