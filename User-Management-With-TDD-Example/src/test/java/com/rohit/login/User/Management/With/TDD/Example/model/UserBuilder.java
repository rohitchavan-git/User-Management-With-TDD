package com.rohit.login.User.Management.With.TDD.Example.model;

import java.util.UUID;

public class UserBuilder {
    private String userid = UUID.randomUUID().toString();
    private String username = "username";
    private String password = "password";
    private String about = "about";

    public static UserBuilder aUser(){
        return new UserBuilder();
    }

    public UserBuilder userid(String userid) {
        this.userid = userid;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder about(String about) {
        this.about = about;
        return this;
    }

    public User build() {
        return new User(userid, username, password, about);
    }
}