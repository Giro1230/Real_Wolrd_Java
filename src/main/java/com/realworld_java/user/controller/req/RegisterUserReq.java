package com.realworld_java.user.controller.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserReq {
  private String username;
  private String email;
  private String password;
}
