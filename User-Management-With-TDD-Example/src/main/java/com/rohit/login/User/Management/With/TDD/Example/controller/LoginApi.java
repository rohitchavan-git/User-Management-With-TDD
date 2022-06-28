package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.rohit.login.User.Management.With.TDD.Example.Service.LoginService;
import com.rohit.login.User.Management.With.TDD.Example.exception.BadCredentialsException;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserCrentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class LoginApi {
    private final LoginService loginService;

    public LoginApi(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public User login(UserCrentials userInfo,
                      HttpServletResponse httpServletResponse) throws BadCredentialsException {

        Optional<User> userLogin = loginService.login(userInfo);
        if (userLogin.isPresent()) {
            return prepareOKResponse(httpServletResponse, userLogin);
        }
        httpServletResponse.setStatus(404);
        throw new BadCredentialsException("invalid credentials. ");
    }

    private User prepareOKResponse(HttpServletResponse httpServletResponse, Optional<User> userLogin) {
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("application/json");
        return userLogin.orElse(null);
    }

}
