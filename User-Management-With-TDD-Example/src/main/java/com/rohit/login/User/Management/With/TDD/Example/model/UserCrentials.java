package com.rohit.login.User.Management.With.TDD.Example.model;

import java.util.Objects;
import java.util.Optional;

public class UserCrentials {
    private final String username;
    private final String password;

    public UserCrentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isMatch(User user) {
        return Objects.equals(user.getUsernameOpt().orElse(null), username) &&
                Objects.equals(user.getPasswordOpt().orElse(null), password);
    }

}
