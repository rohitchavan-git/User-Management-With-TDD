package com.rohit.login.User.Management.With.TDD.Example.repo;

import com.rohit.login.User.Management.With.TDD.Example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private List<User> allUser=new ArrayList<>();

    public User add(User registrationData) {
        allUser.add( registrationData );
        return registrationData;
    }

    public boolean isUserTaken(String username) {

        return Optional.ofNullable(allUser).stream()
                .flatMap(Collection::stream)
                .map( User::getUsername )
                .anyMatch(username::equals);
    }
}
