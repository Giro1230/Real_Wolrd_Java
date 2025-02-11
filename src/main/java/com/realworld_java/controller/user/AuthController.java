package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.LoginUserReq;
import com.realworld_java.controller.user.req.RegisterUserReq;
import com.realworld_java.controller.user.req.UserReq;
import com.realworld_java.controller.user.res.LoginUserRes;
import com.realworld_java.controller.user.res.RegisterUserRes;
import com.realworld_java.controller.user.res.UserRes;
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
    public ResponseEntity<UserRes> createUser(@RequestBody UserReq user) {
        logger.info("Create user: {}", user.getUser());
        UserRes userRes = userService.register(user);
        return ResponseEntity.ok(userRes);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRes> login(@RequestBody UserReq user) {
        logger.info("Login user: {}", user);
        UserRes userRes = userService.login(user);
        return ResponseEntity.ok(userRes);
    }
}
