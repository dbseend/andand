package com.seesun.andand.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyException extends RuntimeException {

    private String result;

    private ErrorCode errorCode;

    private String message;

    public MyException(ErrorCode errorCode) {
        this.result = "ERROR";
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
