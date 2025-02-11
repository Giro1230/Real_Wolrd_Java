package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.CurrentUserReq;
import com.realworld_java.controller.user.req.UpdateUserReq;
import com.realworld_java.controller.user.req.UserReq;
import com.realworld_java.controller.user.res.CurrentUserRes;
import com.realworld_java.controller.user.res.UpdatedUserRes;
import com.realworld_java.controller.user.res.UserRes;
import com.realworld_java.service.user.UserServiceImpl;
import com.realworld_java.service.user.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping
    public ResponseEntity<UserRes> getCurrentUser(@AuthenticationPrincipal UserReq user) {
        logger.info("Get current user: {}", user);
        UserRes userRes = userService.getCurrentUser(user);
        return ResponseEntity.ok(userRes);
    }

    @PutMapping
    public ResponseEntity<UserRes> updateUser(@AuthenticationPrincipal String email, @RequestBody UserReq user) {
        logger.info("Update user: {}", user);
        UserRes userRes = userService.update(email, user);
        return ResponseEntity.ok(userRes);
    }
}
