package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.CurrentUserReq;
import com.realworld_java.controller.user.req.UserReq;
import com.realworld_java.controller.user.res.UserRes;
import com.realworld_java.service.user.UserServiceImpl;
import com.realworld_java.service.user.inf.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Logger logger;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }

    @GetMapping
    public ResponseEntity<UserRes> getCurrentUser(@AuthenticationPrincipal String userId) {
        logger.info("Get current userId: {}", userId);
        UserRes userRes = userService.getCurrentUser(Long.parseLong(userId));
        return ResponseEntity.ok(userRes);
    }

    @PutMapping
    public ResponseEntity<UserRes> updateUser(@AuthenticationPrincipal Long userId, @RequestBody UserReq user) {
        logger.info("userId : {}", userId);
        logger.info("Update user: {}", user);
        UserRes userRes = userService.update(userId, user);
        return ResponseEntity.ok(userRes);
    }
}
