package com.rohit.login.User.Management.With.TDD.Example.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IdGenerator {

    public String next(){
        return UUID.randomUUID().toString();
    }
}
