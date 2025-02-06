package com.realworld_java.user.controller.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserReq {
  private String email;
}
