package com.realworld_java.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", "아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ARTICLE_NOT_FOUND("ARTICLE_NOT_FOUND", "해당 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    TAG_NOT_FOUND("TAG_NOT_FOUND", "해당 태그를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    EXPIRED_JWT("EXPIRED_JWT", "로그인이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
