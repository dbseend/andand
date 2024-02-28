package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.auth.service.AuthSubService;

import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.dto.response.MateResponse;
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
    private final MateRepository mateRepository;

    // 회원정보 조회 메소드
    public AppUserResponse getAppUser(String appUserId) {

        AppUser appUser = authSubService.findUserByUserId(appUserId);
        log.info("mate list: " + appUser.getAppUserMateList());

        List<MateResponse> mateList = appUser.getAppUserMateList().stream()
                .map(appUserMate -> new MateResponse(appUserMate.getMate()))
                .toList();

        return new AppUserResponse(appUser, mateList);
    }

    // 회원정보 수정 메소드
    public AppUserResponse updateAppUser(AppUserUpdateRequest appUserUpdateRequest) throws Exception {

        AppUser appUser = authSubService.findUserById(appUserUpdateRequest.getAppUserId());

        String newProfileImage = utilService.uploadImage(appUserUpdateRequest.getProfileImage(), PROFILE_DIRECTORY);

        appUser.updateInfo(appUserUpdateRequest.getName(), appUserUpdateRequest.getAge(), appUserUpdateRequest.getPhoneNumber(), newProfileImage);
        appUserRepository.save(appUser);
        log.info("new id: " + appUser.getUserId() + " new name: " + appUser.getName() + " new age: " + appUser.getAge() + " new phone: " + appUser.getPhoneNumber() + " new profile: " + appUser.getProfileImage());

        return new AppUserResponse(appUser);
    }


    // 메이트 등록 메소드
    public void connectMate(ConnectMateRequest connectMateRequest) {

        AppUser appUser = appUserSubService.findAppUserById(connectMateRequest.getAppUserId());

        Mate mate = appUserSubService.findMateByCode(connectMateRequest.getUserCode());

        appUserSubService.connectMate(appUser, mate);
    }

    // 메이트 삭제 메소드
    public void disconnectMate(Long appUserId) {
        AppUser appUser = appUserSubService.findAppUserById(appUserId);

        appUser.getAppUserMateList().stream()
                .map(AppUserMate::getMate)
                .filter(mate -> mate.getAppUserMateList().size() == 2)
                .forEach(mate -> {
                    log.info("mate.getAppUserMateList().size() : " + mate.getAppUserMateList().size());
                    mateRepository.delete(mate);
                });
    }
}
