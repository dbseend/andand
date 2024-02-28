package com.seesun.andand.auth.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
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
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    // 아이디로 사용자 조회 메소드
    public AppUser findUserByUserId(String userId) {
        return appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }

    public AppUser findUserById(Long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }
}
