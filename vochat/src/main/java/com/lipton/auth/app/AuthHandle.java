package com.lipton.auth.app;

import com.lipton.auth.entities.UserEntity;
import com.lipton.auth.models.UserDto;
import com.lipton.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Component
public class AuthHandle {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthHandle(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDto handleRegister(String username, String password, String confirmPassword) {
        if (!Objects.equals(password, confirmPassword))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords are not equal");

        if (username.length() > 255)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must not exceed 255 characters");

        var hashPassword = passwordEncoder.encode(password);
        var inserted = userRepository.insertUniqueUser(username, hashPassword);

        if (inserted == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");

        return new UserDto(username);
    }

    public UserDto handleLogin(String username, String password) {
        var user = userRepository.findByUsername(username);

        var hashPassword = user.map(UserEntity::getPassword).orElse("some_user_password");

        if (!passwordEncoder.matches(password, hashPassword))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");

        return new UserDto(username);
    }
}
