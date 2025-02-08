package com.realworld_java.controller.user.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserReq {
  private String email;
  private String password;
}
