package com.rohit.login.User.Management.With.TDD.Example.Service;

import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserCrentials;
import com.rohit.login.User.Management.With.TDD.Example.repo.UserRepository;

import java.util.Objects;
import java.util.Optional;

public class LoginService {
    private final UserRepository loginRepository;

    public LoginService(UserRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Optional<User> login(UserCrentials user) {
        Objects.requireNonNull(user,"Please provided username & password");
        Objects.requireNonNull(user.getUsername(),"Please provide username");
        Objects.requireNonNull(user.getPassword(),"Please provide password");
        return loginRepository.login(user);
    }
}
