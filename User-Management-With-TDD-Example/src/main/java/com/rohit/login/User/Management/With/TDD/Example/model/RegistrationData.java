package com.rohit.login.User.Management.With.TDD.Example.model;

public class RegistrationData {
    private final String username;
    private final String password;
    private final String about;

    public RegistrationData(String username, String password, String about) {

        this.username = username;
        this.password = password;
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }
}
