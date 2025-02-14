package com.realworld_java.service.user;

import com.realworld_java.controller.user.req.*;
import com.realworld_java.controller.user.res.*;
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
        UserReq userReq = UserReq.builder()
                .user(UserReq.UserReqDTO.builder()
                        .username("testUser")
                        .email("test@example.com")
                        .password("password123")
                        .build())
                .build();

        // when
        UserRes resUser = userService.register(userReq);

        // then
        assertThat(resUser).isNotNull();
        assertThat(resUser.getUser().getToken()).isNotNull();
        assertThat(resUser.getUser().getEmail()).isEqualTo(userReq.getUser().getEmail());
        assertThat(resUser.getUser().getUsername()).isEqualTo(userReq.getUser().getUsername());
    }

    @Test
    void login() {
        // given
        UserReq userReq = UserReq.builder()
                .user(UserReq.UserReqDTO.builder()
                        .username("testUser")
                        .email("test@example.com")
                        .password("password123")
                        .build())
                .build();

        // when
        UserRes registerUser = userService.register(userReq);
        UserRes loginUserRes = userService.login(userReq);

        // then
        assertThat(loginUserRes).isNotNull();
        assertThat(loginUserRes.getUser().getToken()).isNotNull();
        assertThat(loginUserRes.getUser().getEmail()).isEqualTo(registerUser.getUser().getEmail());
    }

    @Test
    void getCurrentUser() {
        // given : save
        UserReq reqUser = UserReq
                .builder()
                .user(UserReq.UserReqDTO.builder()
                        .email("test@example.com")
                        .password("password123")
                        .username("testUser")
                        .build())
                .build();

        UserRes registerUser = userService.register(reqUser);

        // when
        UserRes currentUserRes = userService.getCurrentUser(1L);

        // then
        assertThat(currentUserRes).isNotNull();
        assertThat(currentUserRes.getUser().getToken()).isNotNull();
        assertThat(currentUserRes.getUser().getEmail()).isEqualTo(registerUser.getUser().getEmail());
        assertThat(currentUserRes.getUser().getUsername()).isEqualTo(registerUser.getUser().getUsername());
    }

    @Test
    void update() {
        // given : save
        UserReq reqUser = UserReq
                .builder()
                .user(UserReq.UserReqDTO.builder()
                        .email("test@example.com")
                        .password("password123")
                        .username("testUser")
                        .build())
                .build();

        UserRes registerUser = userService.register(reqUser);

        String updateUserEmail = "update@example.com";

        UserReq updateUser = UserReq.builder()
                .user(UserReq.UserReqDTO.builder()
                        .email(updateUserEmail)
                        .build())
                .build();

        // when
        UserRes updatedUserRes = userService.update(1L, updateUser);


        // then
        assertThat(updatedUserRes).isNotNull();
        assertThat(updatedUserRes.getUser().getEmail()).isEqualTo(updateUserEmail);
        assertThat(updatedUserRes.getUser().getUsername()).isEqualTo(registerUser.getUser().getUsername());
    }
}