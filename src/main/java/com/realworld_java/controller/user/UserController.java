package com.realworld_java.controller.user;

import com.realworld_java.controller.user.req.CurrentUserReq;
import com.realworld_java.controller.user.req.UpdateUserReq;
import com.realworld_java.controller.user.res.CurrentUserRes;
import com.realworld_java.controller.user.res.UpdatedUserRes;
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
