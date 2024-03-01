package com.seesun.andand.auth.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.exception.ErrorCode;
import com.seesun.andand.exception.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSubService {

    private final AppUserRepository appUserRepository;

    // 아이디 중복 체크 메소드
    public void checkIdAvailability(String userId) {
        Optional<AppUser> appUser = appUserRepository.findByUserId(userId);
        if (appUser.isPresent()) {
            throw new MyException(ErrorCode.USER_ALREADY_EXIST);
        }
    }

    // 아이디로 사용자 조회 메소드
    public AppUser findUserByUserId(String userId) {
        return appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new MyException(ErrorCode.USER_NOT_FOUND));
    }
}
