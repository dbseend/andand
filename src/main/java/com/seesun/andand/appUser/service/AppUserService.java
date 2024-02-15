package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.request.SignUpRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final MateRepository mateRepository;
    private final AppUserMateRepository appUserMateRepository;
    private final UtilService utilService;

    // 회원가입 메소드
    public void signUp(SignUpRequest signUpRequest) {

        String userCode = utilService.generateRandomCode(6);
        Long initialPoint = 0L;

        Mate newNate = new Mate(userCode, 0, 0);
        mateRepository.save(newNate);

        AppUser newAppUser = new AppUser(
                signUpRequest.getName(),
                signUpRequest.getAge(),
                signUpRequest.getPhoneNumber(),
                userCode,
                initialPoint);
        appUserRepository.save(newAppUser);

        AppUserMate newAppUserMate = new AppUserMate(newAppUser, newNate);
        appUserMateRepository.save(newAppUserMate);
    }

    // 회원정보 조회 메소드
    public AppUserResponse getAppUser(Long appUserId) {

        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        return new AppUserResponse(
                appUser.getId(),
                appUser.getName(),
                appUser.getAge(),
                appUser.getPhoneNumber(),
                appUser.getUserCode());
    }

    // 회원정보 수정 메소드
    public void updateAppUser(Long appUserId, AppUserUpdateRequest appUserUpdateRequest) {

        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        appUser.updateInfo(appUserUpdateRequest.getName(), appUserUpdateRequest.getPhoneNumber());
        appUserRepository.save(appUser);
    }
}
