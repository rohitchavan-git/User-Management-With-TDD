package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.rohit.login.User.Management.With.TDD.Example.Service.UserService;
import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.util.UsernameAlreadyInUseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public User create(@RequestBody RegistrationData registrationData,
                       HttpServletResponse response) throws IOException {

        try {
            User user = userService.createUser(registrationData);
            response.setStatus(201);
            response.setContentType("application/json");
            return user;
        } catch (UsernameAlreadyInUseException e) {
            response.setStatus(400);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "User Already In Use");
        }
        return null;

    }
}
