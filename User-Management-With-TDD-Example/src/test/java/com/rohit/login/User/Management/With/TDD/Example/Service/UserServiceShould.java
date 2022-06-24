package com.rohit.login.User.Management.With.TDD.Example.Service;

import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.repo.UserRepository;
import com.rohit.login.User.Management.With.TDD.Example.util.IdGenerator;
import com.rohit.login.User.Management.With.TDD.Example.util.UsernameAlreadyInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rohit.login.User.Management.With.TDD.Example.model.UserBuilder.aUser;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceShould {

    private static final String USERNAME = "rohit";
    private static final String PASSWORD = "rohit@123";
    private static final String ABOUT = "About Rohit";
    private static final RegistrationData REGISTRATION_DATA =
            new RegistrationData(USERNAME,PASSWORD,ABOUT);
    private static final String USER_ID = randomUUID().toString();
    private static final User USER = aUser()
                                    .userid(USER_ID)
                                    .username(USERNAME)
                                    .password(PASSWORD)
                                    .about(ABOUT)
                                .build();
    UserService userService ;
    @Mock  UserRepository userRepository;

    @Mock IdGenerator idGenerator;

    @BeforeEach public void
    initialise() {
        userService=new UserService(userRepository,idGenerator);
    }

    @Test public void
    crate_a_new_user() throws UsernameAlreadyInUseException {
        given(idGenerator.next()).willReturn(USER_ID);
        given( userRepository.isUserTaken(USERNAME ) ).willReturn(false);
        User result=userService.createUser(REGISTRATION_DATA);
        verify(userRepository).add(USER);
        assertEquals(result,USER);
    }

    @Test public void
    return_a_error_while_attempting_to_create_duplicate_user() {
        given(userRepository.isUserTaken(USERNAME)).willReturn(true);
        assertThrows(UsernameAlreadyInUseException.class,
                ()->userService.createUser(REGISTRATION_DATA));
    }



}