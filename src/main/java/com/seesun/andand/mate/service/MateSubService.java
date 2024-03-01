package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.exception.ErrorCode;
import com.seesun.andand.exception.MyException;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MateSubService {

    private final MateRepository mateRepository;
    private final AppUserMateRepository appUserMateRepository;
    private final DailyRepository dailyRepository;

    // 메이트 생성 메소드
    public void createMate(AppUser appUser) {

        Mate mate = new Mate(appUser.getUserCode());
        mateRepository.save(mate);

        AppUserMate appUserMate = new AppUserMate(appUser, mate);
        appUserMateRepository.save(appUserMate);
    }

    // 메이트 연결 메소드
    public void connectMate(AppUser appUser, Mate mate) {
        AppUserMate appUserMate = new AppUserMate(appUser, mate);
        appUserMateRepository.save(appUserMate);
    }

    // 코드로 메이트 조회 메소드
    public Mate findMateByCode(String userCode) {
        return mateRepository.findByCode(userCode)
                .orElseThrow(() -> new MyException(ErrorCode.MATE_NOT_FOUND));
    }

    // 연속 일수 갱신 메소드
    public void updateContinuousDays(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<Daily> yesterdayDailyList = dailyRepository.findByCreateDateBetween(startDateTime, endDateTime);
        for (Daily daily : yesterdayDailyList) {
            if (!daily.getIsBothUploaded()) {
                Mate mate = daily.getMate();
                mate.initDailyContinuousDays(); // 메이트의 연속일 수 초기화
                mateRepository.save(mate);
            }
        }
    }

    // 오늘의 키워드 할당
    public void assignTodayKeyword(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        String todayKeyword = "오늘의 키워드";

        List<Daily> yesterdayDailyList = dailyRepository.findByCreateDateBetween(startDateTime, endDateTime);
        List<Daily> todayDailyList = new ArrayList<>();
        for (Daily daily : yesterdayDailyList) {
            Daily todayDaily = new Daily();

            if (daily.getAppUserDailyList().isEmpty()) { // 어제의 데일리에 등록된 사용자가 없는 경우
                todayDaily = new Daily(daily.getTag(), daily.getMate(), false);
            }
            if (!daily.getAppUserDailyList().isEmpty()) { // 어제의 데일리에 등록된 사용자가 있는 경우
                todayDaily = new Daily(todayKeyword, daily.getMate(), false);
            }
            todayDailyList.add(todayDaily);
        }
        dailyRepository.saveAll(todayDailyList);
    }
}
