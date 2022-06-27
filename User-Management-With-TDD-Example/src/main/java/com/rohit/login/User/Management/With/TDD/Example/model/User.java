package com.rohit.login.User.Management.With.TDD.Example.model;

public class User {
    private  String userid;
    private final String username;
    private final String password;
    private final String about;
    public User(String userid, String username, String password, String about) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.about = about;
    }
    public String getUserid() {
        return userid;
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
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (userid != null ? !userid.equals(user.userid) : user.userid != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return about != null ? about.equals(user.about) : user.about == null;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (about != null ? about.hashCode() : 0);
        return result;
    }
}



