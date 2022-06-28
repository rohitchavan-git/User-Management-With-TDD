package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.rohit.login.User.Management.With.TDD.Example.Service.LoginService;
import com.rohit.login.User.Management.With.TDD.Example.exception.BadCredentialsException;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserCrentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoginApiShould {

    private static final User USER = aUser().build();
    private static final String PASSWORD = "PASSWORD";
    private static final String USERNAME = "USERNAME";
    private static final UserCrentials USER_CREDENTIALS =
            new UserCrentials(USERNAME,PASSWORD);
    @Mock
    private LoginService loginService;
    private LoginApi loginApi;
    @Mock
    private HttpServletResponse httpServletResponse;


    @BeforeEach
    public void
    initialise() {
        loginApi=new LoginApi(loginService);
    }

    @Test
    public void
    return_json_represention_of_a_valid_user() throws BadCredentialsException {

        given(loginService.login(USER_CREDENTIALS))
                .willReturn(Optional.of(USER));
        User result = loginApi.login(USER_CREDENTIALS, httpServletResponse);
        verify(httpServletResponse).setStatus(200);
        verify(httpServletResponse).setContentType("application/json");
        assertEquals(result, USER);
    }

    @Test
    public void
    return_an_error_for_invalid_credentials() throws BadCredentialsException {
        given(loginService.login(USER_CREDENTIALS))
                .willReturn(Optional.empty());
        assertThrows(BadCredentialsException.class,
                this::prepareInvalidResponse);

    }

    private void prepareInvalidResponse() throws BadCredentialsException {
        loginApi.login(USER_CREDENTIALS,httpServletResponse);
        verify(httpServletResponse).setStatus(404);
    }
}