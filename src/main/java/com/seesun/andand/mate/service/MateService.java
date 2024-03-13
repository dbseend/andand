package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.exception.ErrorCode;
import com.seesun.andand.exception.MyException;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.dto.response.MateResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateService {

    private final AppUserSubService appUserSubService;
    private final MateSubService mateSubService;
    private final AppUserMateRepository appUserMateRepository;

    // 데일리 자동 생성 및 연속 날짜 갱신 메소드
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void everyDay() {

        LocalDate yesterday = LocalDate.now().minusDays(1); // 어제의 날짜를 얻습니다.
        LocalDateTime startDateTime = yesterday.atStartOfDay();
        LocalDateTime endDateTime = yesterday.atTime(23, 59, 59);

        mateSubService.updateContinuousDays(startDateTime, endDateTime); // 연속일수 갱신
        mateSubService.assignTodayKeyword(startDateTime, endDateTime); // 데일리 생성
    }

    // 메이트 등록 메소드(연결 확인 버튼 클릭 시)
    @Transactional
    public void connectMate(ConnectMateRequest connectMateRequest) {

        AppUser appUser = appUserSubService.findAppUserById(connectMateRequest.getUserId());

        Mate mate = mateSubService.findMateByCode(connectMateRequest.getUserCode());

        mateSubService.connectMate(appUser, mate);
    }

    // 메이트 삭제 메소드
    @Transactional
    public void disconnectMate(Long appUserId, Long mateId) {
        appUserMateRepository.deleteByAppUserIdAndMateId(appUserId, mateId);
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
                .orElseThrow(() -> new MyException(ErrorCode.MATE_NOT_FOUND));

        return new MateResponse(mate);
    }
}
