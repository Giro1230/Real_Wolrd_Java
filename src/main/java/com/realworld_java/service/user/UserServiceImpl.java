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
    public UserRes register(UserReq data) {
        // findByEmail
        if (userRepository.findByEmail(data.getUser().getEmail()).isPresent())
            throw new InvalidCredentialsException("Email already in use");

        // password encode
        final String encodedPassword = passwordEncoder.encode(data.getUser().getPassword());

        // RegisterUserReq -> User
        final User user = User.builder()
                .username(data.getUser().getUsername())
                .email(data.getUser().getEmail())
                .password(encodedPassword)
                .build();

        final User savedUser = userRepository.save(user);

        return converter(savedUser, jwt.generateToken(savedUser));
    }

    @Override
    public UserRes login(UserReq data) {
        // getUserByEmail
        final User user = userRepository.findByEmail(data.getUser().getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // password match
        if (!passwordEncoder.matches(data.getUser().getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // token
        final String token = jwt.generateToken(user);

        return converter(user, token);
    }

    @Override
    public UserRes getCurrentUser(Long userId) {
        // getUserByEmail
        final User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidCredentialsException("Not Login"));

        // token
        final String token = jwt.generateToken(findUser);

        return converter(findUser, token);
    }

    @Override
    public UserRes update(Long userId, UserReq data) {
        // getUserById
        final User user = userRepository.findById(userId)
                .orElseThrow(()-> new InvalidCredentialsException("Invalid user id"));

        // updatedUser
        user.updated(data.getUser().getEmail());
        logger.info("updated user: {}", user);

        userRepository.save(user);

        // token
        final String token = jwt.generateToken(user);

        return converter(user, token);
    }

    // User(Entity) -> UserRes(DTO)
    public UserRes converter (User user, String token) {
        return UserRes.fromUser(user, token);
    }
}
