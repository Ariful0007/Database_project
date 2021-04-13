package com.example.database_project;

public class user_data {
    String username,password,class_sign;

    public user_data() {
    }

    public user_data(String username, String password, String class_sign) {
        this.username = username;
        this.password = password;
        this.class_sign = class_sign;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClass_sign() {
        return class_sign;
    }

    public void setClass_sign(String class_sign) {
        this.class_sign = class_sign;
    }
}
