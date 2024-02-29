package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.dto.response.MateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateService {

    private final MateRepository mateRepository;
    private final AppUserSubService appUserSubService;
    private final MateSubService mateSubService;
    private final AppUserMateRepository appUserMateRepository;

    // 메이트 등록 메소드(연결 확인 버튼 클릭 시)
    @Transactional
    public void connectMate(ConnectMateRequest connectMateRequest) {

        AppUser appUser = appUserSubService.findAppUserById(connectMateRequest.getUserId());

        Mate mate = mateSubService.findMateByCode(connectMateRequest.getUserCode());

        mateSubService.connectMate(appUser, mate);
    }

    // 메이트 삭제 메소드
    @Transactional
    public void disconnectMate(String userId) {

        AppUser appUser = appUserSubService.findAppUserById(userId);

        Mate mate = appUser.getAppUserMateList().stream()
                .map(AppUserMate::getMate)
                .filter(mate1 -> mate1.getAppUserMateList().size() == 2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("메이트가 존재하지 않습니다."));

        AppUserMate appUserMate = appUserMateRepository.findByAppUserAndMate(appUser, mate)
                .orElseThrow(() -> new IllegalArgumentException("메이트가 존재하지 않습니다."));

        appUserMateRepository.deleteById(appUserMate.getId());
    }

    // 메이트 조회 메소드
    @Transactional
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
