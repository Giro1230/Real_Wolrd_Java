package com.realworld_java.controller.user.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserReq {
    private CurrentUserReqDTO user;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CurrentUserReqDTO {
        private Long userId;
        private String username;
        private String email;
        private String bio;
        private String image;
    }
}
