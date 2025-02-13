package com.realworld_java.security.jwt;

import com.realworld_java.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Jwt {

  private final SecretKey secretKey;
  private final long expirationTime;

  public Jwt(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long expirationTime) {
//    this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    this.expirationTime = expirationTime;
  }

  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expirationTime);

    return Jwts.builder()
      .setSubject(String.valueOf(user.getId()))
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact();
  }

  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  public Authentication getAuthentication(String token) {
    Claims claims = parseToken(token);

    Long userId = Long.parseLong(claims.getSubject());

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    return new UsernamePasswordAuthenticationToken(userId, "", authorities);
  }
}
