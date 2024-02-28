package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MateService {

    private final MateRepository mateRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserMateRepository appUserMateRepository;
    private final AppUserSubService appUserSubService;
    private final MateSubService mateSubService;

    // 메이트 등록 메소드
    public void connectMate(ConnectMateRequest connectMateRequest) {

        AppUser appUser = appUserSubService.findById(connectMateRequest.getAppUserId());

        Mate mate = mateSubService.findMateByCode(connectMateRequest.getUserCode());

        mateSubService.connectMate(appUser, mate);
    }

    // 메이트 삭제 메소드
    public void disconnectMate(Long appUserId, Long mateId) {

        // 사용자 정보 조회
        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        // 메이트 정보 조회
        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        // 사용자와 메이트 연결 삭제
        AppUserMate appUserMate = appUserMateRepository.findByAppUserAndMate(appUser, mate).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자와 메이트 연결이 없습니다."));
        appUserMateRepository.delete(appUserMate);
    }
}
