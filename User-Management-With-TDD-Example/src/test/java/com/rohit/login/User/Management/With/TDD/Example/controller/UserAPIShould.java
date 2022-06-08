package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohit.login.User.Management.With.TDD.Example.Service.UserService;
import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserAPIShould {


    private static final String USERNAME = "Rohit";
    private static final String PASSWORD = "Rohit@123";
    private static final String ABOUT = "About Rohit";
    private static final RegistrationData REGISTRATION_DATA =
            new RegistrationData(USERNAME,PASSWORD,ABOUT);
    private static final String USERID = UUID.randomUUID().toString();
    private static final User USER = aUser()
                                            .userid(USERID)
                                            .username(USERNAME)
                                            .password(PASSWORD)
                                            .about(ABOUT)
                                        .build();

    private static final User ROHIT=aUser().build();
    private static final User URMILA=aUser().build();
    UserAPI userApi;
    @Mock UserService userService;

    @Mock HttpServletResponse response;


    @BeforeEach
    public void
    initialise() {
        userApi=new UserAPI(userService);
    }


    @Test
    public void
    create_a_new_user() {

        userApi.create(REGISTRATION_DATA,response);
        verify(userService).createUser(REGISTRATION_DATA);

    }

    @Test
    public void
    return_a_json_representing_newly_created_user() {

        given(userService.createUser(REGISTRATION_DATA)).willReturn(USER);
        User result = userApi.create(REGISTRATION_DATA,response);

        verify(response).setStatus(200);
        verify(response).setContentType("application/json");

        assertEquals(result,USER);

    }

    @Test
    public void
    throw_an_exception_when_attenping_to_create_same_user() {



    }

}