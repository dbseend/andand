package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.auth.service.AuthSubService;

import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private static final String PROFILE_DIRECTORY = "profile";

    private final AppUserRepository appUserRepository;
    private final AuthSubService authSubService;
    private final AppUserSubService appUserSubService;
    private final UtilService utilService;

    // 회원정보 조회 메소드
    public AppUserResponse getAppUser(String appUserId) {

        AppUser appUser = authSubService.findUserByUserId(appUserId);

        return new AppUserResponse(appUser);
    }

    // 회원정보 수정 메소드
    public AppUserResponse updateAppUser(AppUserUpdateRequest appUserUpdateRequest) throws Exception {

        AppUser appUser = authSubService.findUserByUserId(appUserUpdateRequest.getUserId());

        String newProfileImage = utilService.uploadProfileImage(appUserUpdateRequest.getProfileImage(), PROFILE_DIRECTORY, appUserUpdateRequest.getUserId());
        log.info("new profile: " + newProfileImage);
        appUser.updateInfo(appUserUpdateRequest.getName(), appUserUpdateRequest.getAge(), appUserUpdateRequest.getPhoneNumber(), newProfileImage);
        appUserRepository.save(appUser);

        return new AppUserResponse(appUser);
    }

    // 파트너 정보 조회 메소드
    public PartnerResponse findAppUserSummaryByCode(String userCode) {
        return appUserSubService.findPartnerInfoByUserId(userCode);
    }
}
