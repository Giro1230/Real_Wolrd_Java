package com.realworld_java.controller.user.res;

import com.realworld_java.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRes {
    private UserResDTO user;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResDTO {
        private String email;
        private String username;
        private String bio;
        private String image;
        private String token;
    }

    public static UserRes fromUser(User user, String token) {
        return UserRes.builder()
                .user(UserResDTO.builder()
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .bio(user.getBio())
                        .image(user.getImage())
                        .token(token)
                        .build())
                .build();
    }
}
