package com.realworld_java.controller.user.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserReq {
    private UpdateUserReqDTO user;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateUserReqDTO {
        private Long userId;
        private String username;
        private String email;
        private String bio;
        private String image;
    }
}
