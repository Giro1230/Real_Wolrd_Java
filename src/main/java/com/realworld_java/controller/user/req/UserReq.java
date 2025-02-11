package com.realworld_java.controller.user.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReq {
    private UserReqDTO user;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserReqDTO {
        private String username;
        private String email;
        private String password;
    }
}
