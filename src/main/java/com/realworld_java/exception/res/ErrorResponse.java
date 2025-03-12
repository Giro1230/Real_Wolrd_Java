package com.realworld_java.exception.res;

import com.realworld_java.exception.ErrorCode;
import lombok.*;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
