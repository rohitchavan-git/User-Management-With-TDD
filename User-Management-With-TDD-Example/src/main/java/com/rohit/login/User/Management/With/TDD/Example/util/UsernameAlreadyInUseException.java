package com.rohit.login.User.Management.With.TDD.Example.util;

public class UsernameAlreadyInUseException extends Exception {

    public UsernameAlreadyInUseException(Exception e){
        super(e);
    }
    public UsernameAlreadyInUseException(String exception){
        super(exception);
    }

    public UsernameAlreadyInUseException() {
        super();
    }
}
