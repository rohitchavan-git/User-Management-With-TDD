package com.rohit.login.User.Management.With.TDD.Example.controller;

import com.rohit.login.User.Management.With.TDD.Example.Service.UserService;
import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.util.UsernameAlreadyInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    UserAPI userApi;
    @Mock UserService userService;

    @Mock HttpServletResponse response;


    @BeforeEach
    public void
    initialise() throws IOException {
        userApi=new UserAPI(userService);
        userApi.create(REGISTRATION_DATA,response);
    }


    @Test
    public void
    create_a_new_user() throws UsernameAlreadyInUseException, IOException {
        verify(userService).createUser(REGISTRATION_DATA);
    }

    @Test
    public void
    return_a_json_representing_newly_created_user() throws UsernameAlreadyInUseException, IOException {
        given(userService.createUser(REGISTRATION_DATA)).willReturn(USER);
        User result = userApi.create(REGISTRATION_DATA,response);
        verify(response).setStatus(201);
        verify(response).setContentType("application/json");
        assertEquals(result,USER);
    }

    @Test()
    public void
    throw_an_error_when_attending_to_create_same_user() throws IOException, UsernameAlreadyInUseException {

        userApi.create(REGISTRATION_DATA,response);
        given(userService.createUser(REGISTRATION_DATA)).willThrow(UsernameAlreadyInUseException.class);
        assertThrows(UsernameAlreadyInUseException.class,
                this::handleUserAlreadyInUserException);
    }

    private void handleUserAlreadyInUserException() throws UsernameAlreadyInUseException, IOException {
        userService.createUser(REGISTRATION_DATA);
        verify(response).setStatus(400);
        verify(response).sendError(HttpServletResponse.SC_BAD_REQUEST, "User Already In Use");
    }

}