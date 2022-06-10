package com.rohit.login.User.Management.With.TDD.Example.repo;

import com.rohit.login.User.Management.With.TDD.Example.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User add(User registrationData) {
        throw new UnsupportedOperationException();
    }

    public boolean isUserTaken(String username) {
        throw new UnsupportedOperationException();
    }
}
