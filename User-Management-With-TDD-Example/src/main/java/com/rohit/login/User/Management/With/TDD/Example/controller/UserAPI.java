package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.rohit.login.User.Management.With.TDD.Example.Service.UserService;
import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    public User create(RegistrationData registrationData,
                       HttpServletResponse response) {
        User user = userService.createUser(registrationData);

        response.setStatus(200);
        response.setContentType("application/json");
        return user;
    }
}
