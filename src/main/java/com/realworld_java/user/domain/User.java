package com.realworld_java.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Comment("유저 이름")
  @Column(length = 20, nullable = false)
  private String username;

  @Comment("유저 이메일")
  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Comment("비밀번호")
  @Column(nullable = false)
  private String password;

  @Comment("자기소개")
  private String bio;

  @Comment("이미지")
  private String image;

  @Comment("생성날짜")
  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Comment("수정날짜")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "User{id=" + id +
      ", username='" + username + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", bio='" + bio + '\'' +
      ", image='" + image + '\'' +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      '}';
  }
}
