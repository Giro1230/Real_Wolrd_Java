package com.realworld_java.service.user;

import com.realworld_java.controller.user.req.CurrentUserReq;
import com.realworld_java.controller.user.req.LoginUserReq;
import com.realworld_java.controller.user.req.RegisterUserReq;
import com.realworld_java.controller.user.req.UpdateUserReq;
import com.realworld_java.controller.user.res.CurrentUserRes;
import com.realworld_java.controller.user.res.LoginUserRes;
import com.realworld_java.controller.user.res.RegisterUserRes;
import com.realworld_java.controller.user.res.UpdatedUserRes;
import com.realworld_java.domain.User;
import com.realworld_java.service.user.inf.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Test
    void register() {
        // given
        RegisterUserReq reqUser = RegisterUserReq
                .builder()
                .email("test@example.com")
                .password("password123")
                .username("testUser")
                .build();

        // when
        RegisterUserRes resUser = userService.register(reqUser);

        // then
        assertThat(resUser).isNotNull();
        assertThat(resUser.getToken()).isNotNull();
        assertThat(resUser.getEmail()).isEqualTo(reqUser.getEmail());
        assertThat(resUser.getUsername()).isEqualTo(reqUser.getUsername());
    }

    @Test
    void login() {
        // given
        RegisterUserReq reqUser = RegisterUserReq
                .builder()
                .email("test@example.com")
                .password("password123")
                .username("testUser")
                .build();

        LoginUserReq loginUserReq = LoginUserReq.builder()
                .email(reqUser.getEmail())
                .password(reqUser.getPassword())
                .build();

        // when
        RegisterUserRes resUser = userService.register(reqUser);
        LoginUserRes loginUserRes = userService.login(loginUserReq);

        // then
        assertThat(loginUserRes).isNotNull();
        assertThat(loginUserRes.getToken()).isNotNull();
        assertThat(loginUserRes.getEmail()).isEqualTo(resUser.getEmail());
    }

    @Test
    void getCurrentUser() {
        // given : save
        RegisterUserReq reqUser = RegisterUserReq
                .builder()
                .email("test@example.com")
                .password("password123")
                .username("testUser")
                .build();

        RegisterUserRes resUser = userService.register(reqUser);

        // given : Current Entity
        CurrentUserReq currentUserReq = CurrentUserReq.builder()
                .email(reqUser.getEmail())
                .build();

        // when
        CurrentUserRes currentUserRes = userService.getCurrentUser(currentUserReq);

        // then
        assertThat(currentUserRes).isNotNull();
        assertThat(currentUserRes.getToken()).isNotNull();
        assertThat(currentUserRes.getEmail()).isEqualTo(resUser.getEmail());
        assertThat(currentUserRes.getUsername()).isEqualTo(resUser.getUsername());
    }

    @Test
    void update() {
        // given : save
        RegisterUserReq reqUser = RegisterUserReq
                .builder()
                .email("test@example.com")
                .password("password123")
                .username("testUser")
                .build();

        RegisterUserRes resUser = userService.register(reqUser);

        // given : updated Entity
        UpdateUserReq updateUserReq = UpdateUserReq.builder()
                .email(resUser.getEmail())
                .build();

        String updateUserEmail = "update@example.com";

        // when
        UpdatedUserRes updatedUserRes = userService.update(updateUserEmail, updateUserReq);

        // then
        assertThat(updatedUserRes).isNotNull();
        assertThat(updatedUserRes.getEmail()).isEqualTo(updateUserEmail);
    }
}