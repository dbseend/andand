package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final MateRepository mateRepository;
    private final AppUserMateRepository appUserMateRepository;
    private final UtilService utilService;

    // 회원정보 조회 메소드
    public AppUserResponse getAppUser(Long appUserId) {

        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        return new AppUserResponse(appUser);
    }

    // 회원정보 수정 메소드
    public AppUserResponse updateAppUser(AppUserUpdateRequest appUserUpdateRequest) {

        AppUser appUser = appUserRepository.findById(appUserUpdateRequest.getAppUserId()).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        appUser.updateInfo(appUser);
        appUserRepository.save(appUser);

        return new AppUserResponse(appUser);
    }
}
