package com.realworld_java.service.user;

import com.realworld_java.controller.user.req.*;
import com.realworld_java.controller.user.res.*;
import com.realworld_java.exception.user.InvalidCredentialsException;
import com.realworld_java.security.jwt.Jwt;
import com.realworld_java.domain.User;
import com.realworld_java.repository.UserRepository;
import com.realworld_java.service.user.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Jwt jwt;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, Jwt jwt) {
        this.logger = LoggerFactory.getLogger(UserServiceImpl.class);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Override
    public RegisterUserRes register(RegisterUserReq data) {
        // findByEmail
        if (userRepository.findByEmail(data.getEmail()).isPresent())
            throw new InvalidCredentialsException("Email already in use");

        logger.info("user data : {}", data.getPassword());

        // password encode
        final String encodedPassword = passwordEncoder.encode(data.getPassword());

        // RegisterUserReq -> User
        final User user = User.builder()
                .username(data.getUsername())
                .email(data.getEmail())
                .password(encodedPassword)
                .build();

        final User savedUser = userRepository.save(user);

        return RegisterUserRes.converter(savedUser, jwt.generateToken(savedUser));
    }

    @Override
    public LoginUserRes login(LoginUserReq data) {
        // getUserByEmail
        final User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // password match
        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // token
        final String token = jwt.generateToken(user);

        return LoginUserRes.converter(user, token);
    }

    @Override
    public CurrentUserRes getCurrentUser(CurrentUserReq data) {
        // getUserByEmail
        final User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email"));

        // token
        final String token = jwt.generateToken(user);

        return CurrentUserRes.converter(user, token);
    }

    @Override
    public UpdatedUserRes update(String email, UpdateUserReq data) {
        // getUserByEmail
        final User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email"));

        // updatedUser
        user.updated(email);
        logger.info("updated user: {}", user);

        userRepository.save(user);

        // token
        final String token = jwt.generateToken(user);

        return UpdatedUserRes.converter(user, token);
    }
}
