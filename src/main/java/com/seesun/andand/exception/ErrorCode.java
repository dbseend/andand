package com.seesun.andand.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 유저가 없습니다"),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 등록된 ID입니다"),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
    MATE_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 동료가 없습니다"),
    MATE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 등록된 동료입니다"),
    DAILY_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 일지가 없습니다"),
    PARTNER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 등록된 파트너가 있습니다");

    private HttpStatus status;
    private String message;
}
