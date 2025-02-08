package com.realworld_java.controller.user.res;

import com.realworld_java.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedUserRes {
  private String username;
  private String email;
  private String bio;
  private String image;
  private String token;

  public static UpdatedUserRes converter(User user, String token) {
    return UpdatedUserRes.builder()
            .username(user.getUsername())
            .email(user.getEmail())
            .bio(user.getBio())
            .image(user.getImage())
            .token(token)
            .build();
  }
}
