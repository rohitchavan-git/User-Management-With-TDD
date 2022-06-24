package com.rohit.login.User.Management.With.TDD.Example.Service;

import com.rohit.login.User.Management.With.TDD.Example.model.RegistrationData;
import com.rohit.login.User.Management.With.TDD.Example.model.User;
import com.rohit.login.User.Management.With.TDD.Example.repo.UserRepository;
import com.rohit.login.User.Management.With.TDD.Example.util.IdGenerator;
import com.rohit.login.User.Management.With.TDD.Example.util.UsernameAlreadyInUseException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    public UserService(UserRepository userRepository, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.idGenerator = idGenerator;
    }

    public User createUser(RegistrationData registrationData)
            throws UsernameAlreadyInUseException {
        isValidUser(registrationData);
        User user = userFrom( registrationData );
        userRepository.add(user);
        return user;
    }

    private User userFrom(RegistrationData registrationData) {
        return new User( idGenerator.next(), registrationData.getUsername(),
                registrationData.getPassword(), registrationData.getAbout());
    }

    private void isValidUser(RegistrationData registrationData) throws UsernameAlreadyInUseException {
        if (userRepository.isUserTaken(registrationData.getUsername())) {
            throw new UsernameAlreadyInUseException();
        }
    }

}
