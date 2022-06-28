package com.rohit.login.User.Management.With.TDD.Example.repo;

import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserCrentials;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> allUser=new ArrayList<>();

    public void add(User user) {
        allUser.add(user);
    }

    public boolean isUserTaken(String username) {
        return allUser.stream()
                .map(User::getUsername)
                .anyMatch(username::equals);
    }

    public Optional<User> login(UserCrentials userCredentials) {
        return allUser.stream()
                .filter(userCredentials::isMatch)
                .findFirst();
    }


}
