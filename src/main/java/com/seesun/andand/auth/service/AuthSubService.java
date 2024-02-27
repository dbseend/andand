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

    public void isExistAppUser(String userId) {
        log.info("userId: " + userId);
        Optional<AppUser> appUser = appUserRepository.findByUserId(userId);
        if (appUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }
}
