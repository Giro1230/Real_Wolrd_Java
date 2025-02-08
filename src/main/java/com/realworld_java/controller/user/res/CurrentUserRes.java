package com.realworld_java.controller.user.res;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserRes {
  private String username;
  private String email;
  private String bio;
  private String image;
  private String token;
}
