package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.dto.response.MateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateService {

    private final MateRepository mateRepository;
    private final AppUserSubService appUserSubService;
    private final MateSubService mateSubService;

    // 메이트 등록 메소드(연결 확인 버튼 클릭 시)
    public void connectMate(ConnectMateRequest connectMateRequest) {

        AppUser appUser = appUserSubService.findAppUserById(connectMateRequest.getUserId());

        Mate mate = mateSubService.findMateByCode(connectMateRequest.getUserCode());

        mateSubService.connectMate(appUser, mate);
    }

    // 메이트 삭제 메소드
    public void disconnectMate(String userId) {

        AppUser appUser = appUserSubService.findAppUserById(userId);

        appUser.getAppUserMateList().stream()
                .map(AppUserMate::getMate)
                .filter(mate -> mate.getAppUserMateList().size() == 2)
                .forEach(mate -> {
                    log.info("mate.getAppUserMateList().size() : " + mate.getAppUserMateList().size());
                    mateRepository.delete(mate);
                });
    }

    // 메이트 조회 메소드
    public MateResponse findMate(String userId) {

        AppUser appUser = appUserSubService.findAppUserById(userId);
        List<AppUserMate> appUserMateList = appUser.getAppUserMateList();
        Mate mate = appUserMateList.stream()
                .map(AppUserMate::getMate)
                .filter(appUserMateMate -> appUserMateMate.getAppUserMateList().size() == 2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("메이트가 존재하지 않습니다."));

        return new MateResponse(mate);
    }
}
