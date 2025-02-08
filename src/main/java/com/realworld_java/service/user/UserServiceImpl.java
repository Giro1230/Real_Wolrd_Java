package com.realworld_java.service.user;

import com.realworld_java.controller.user.req.*;
import com.realworld_java.controller.user.res.*;
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
        User user = userRepository.findByEmail(data.getEmail()).orElseThrow();
        return null;
    }

    @Override
    public CurrentUserRes getCurrentUser(CurrentUserReq data) {
        return null;
    }

    @Override
    public UpdatedUserRes update(String email, UpdateUserReq data) {
        return null;
    }
}
