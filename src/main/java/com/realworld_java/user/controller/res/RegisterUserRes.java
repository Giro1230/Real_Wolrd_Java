package com.realworld_java.user.controller.res;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRes {
  private String username;
  private String email;
  private String bio;
  private String image;
  private String token;
}
