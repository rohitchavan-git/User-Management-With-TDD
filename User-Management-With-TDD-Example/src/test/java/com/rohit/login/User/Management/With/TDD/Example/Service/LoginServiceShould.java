package com.rohit.login.User.Management.With.TDD.Example.Service;

import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.model.UserCrentials;
import com.rohit.login.User.Management.With.TDD.Example.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder.aUser;
import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceShould {


    private static final User USER = aUser().build();
    private static final String PASSWORD = "rohit@123";
    private static final String USERNAME = "RohitRko";
    private static final UserCrentials USER_CREDENTIALS =
            new UserCrentials(USERNAME,PASSWORD);
    private LoginService loginService;
    @Mock
    private UserRepository loginRepository;

    @BeforeEach
    public void
    initialise() {
        loginService=new LoginService(loginRepository);
    }

    @Test
    public void
    return_optional_of_user() {
        given(loginRepository.login(USER_CREDENTIALS)).willReturn(of(USER));
        Optional<User> result= loginService.login(USER_CREDENTIALS);
        assertEquals(result, Optional.of(USER));
    }


}