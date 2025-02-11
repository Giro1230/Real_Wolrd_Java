package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.LoginUserReq;
import com.realworld_java.controller.user.req.RegisterUserReq;
import com.realworld_java.controller.user.res.LoginUserRes;
import com.realworld_java.controller.user.res.RegisterUserRes;
import com.realworld_java.service.user.UserServiceImpl;
import com.realworld_java.service.user.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;
    private final Logger logger;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @PostMapping
    public ResponseEntity<RegisterUserRes> createUser(@RequestBody RegisterUserReq user) {
        logger.info("Create user: {}", user.toString());
//        logger.info("Create user: {}", user.getEmail());
//        logger.info("Create user: {}", user.getPassword());
        RegisterUserRes registerUserRes = userService.register(user);
        return ResponseEntity.ok(registerUserRes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserRes> login(@RequestBody LoginUserReq user) {
        logger.info("Login user: {}", user);
        LoginUserRes loginUserRes = userService.login(user);
        return ResponseEntity.ok(loginUserRes);
    }
}
