package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.CurrentUserReq;
import com.realworld_java.controller.user.req.LoginUserReq;
import com.realworld_java.controller.user.req.RegisterUserReq;
import com.realworld_java.controller.user.req.UpdateUserReq;
import com.realworld_java.controller.user.res.CurrentUserRes;
import com.realworld_java.controller.user.res.LoginUserRes;
import com.realworld_java.controller.user.res.RegisterUserRes;
import com.realworld_java.controller.user.res.UpdatedUserRes;
import com.realworld_java.security.jwt.Jwt;
import com.realworld_java.service.user.UserServiceImpl;
import com.realworld_java.service.user.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final Logger logger;
    private final Jwt jwt;

    @Autowired
    public UserController(UserServiceImpl userService, Jwt jwt) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
        this.jwt = jwt;
    }

    @PostMapping
    public ResponseEntity<RegisterUserRes> createUser(@RequestBody RegisterUserReq user) {
        logger.info("Create user: {}", user);
        RegisterUserRes registerUserRes = userService.register(user);
        return ResponseEntity.ok(registerUserRes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserRes> login(@RequestBody LoginUserReq user) {
        logger.info("Login user: {}", user);
        LoginUserRes loginUserRes = userService.login(user);
        return ResponseEntity.ok(loginUserRes);
    }

    @GetMapping
    public ResponseEntity<CurrentUserRes> getCurrentUser(@AuthenticationPrincipal CurrentUserReq user) {
        logger.info("Get current user: {}", user);
        CurrentUserRes currentUserRes = userService.getCurrentUser(user);
        return ResponseEntity.ok(currentUserRes);
    }

    @PutMapping
    public ResponseEntity<UpdatedUserRes> updateUser(@AuthenticationPrincipal String email, @RequestBody UpdateUserReq updateUserReq) {
        logger.info("Update user: {}", updateUserReq);
        UpdatedUserRes updatedUserRes = userService.update(email, updateUserReq);
        return ResponseEntity.ok(updatedUserRes);
    }
}
