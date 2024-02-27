package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.auth.service.AuthSubService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AuthSubService authSubService;

    // 회원정보 조회 메소드
    public AppUserResponse getAppUser(String appUserId) {

        AppUser appUser = authSubService.findUserByUserId(appUserId);

        return new AppUserResponse(appUser);
    }

    // 회원정보 수정 메소드
    public AppUserResponse updateAppUser(AppUserUpdateRequest appUserUpdateRequest) {

        AppUser appUser = authSubService.findUserById(appUserUpdateRequest.getAppUserId());

        appUser.updateInfo(appUser);
        appUserRepository.save(appUser);

        return new AppUserResponse(appUser);
    }
}
