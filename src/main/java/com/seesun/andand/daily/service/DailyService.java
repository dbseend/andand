package com.seesun.andand.daily.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserDaily.domain.AppUserDailyRepository;
import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyService {

    private static final String DAILY = "daily";
    private final AppUserRepository appUserRepository;
    private final MateRepository mateRepository;
    private final DailyRepository dailyRepository;
    private final AppUserDailyRepository appUserDailyRepository;
    private final UtilService utilService;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void everyDay() {

        LocalDate yesterday = LocalDate.now().minusDays(1); // 어제의 날짜를 얻습니다.
        LocalDateTime startDateTime = yesterday.atStartOfDay();
        LocalDateTime endDateTime = yesterday.atTime(23, 59, 59);
        List<Mate> mateList = mateRepository.findAll();

        // 어제 일일 등록이 안된 메이트들의 연속일을 초기화합니다. -> 수정필요
        List<Daily> yesterdayDailyList = dailyRepository.findByCreateDateBetween(startDateTime, endDateTime);
        for (Daily daily : yesterdayDailyList) {
            if (!daily.getIsBothUploaded()) {
                daily.getMate().initDailyContinuousDays();
                mateRepository.save(daily.getMate());
            }
        }

        List<Daily> todayDailyList = mateList.stream()
                .map(mate -> new Daily("웃음", mate, false))
                .toList();

        dailyRepository.saveAll(todayDailyList);
    }

    // 일일 등록 메소드
    public void uploadDaily(Long appUserId, Long mateId, MultipartFile file) throws IOException {

        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        LocalDate targetCreateDate = LocalDate.now(); // 오늘의 날짜와 시간을 얻습니다.
        LocalDateTime startDateTime = targetCreateDate.atStartOfDay();
        LocalDateTime endDateTime = targetCreateDate.atTime(23, 59, 59);
        Daily daily = dailyRepository.findByMateAndCreateDateBetween(mate, startDateTime, endDateTime).
                orElseThrow(() -> new IllegalArgumentException("해당 일일이 없습니다."));

        String picture = utilService.uploadImage(file, DAILY);

        AppUserDaily appUserDaily = new AppUserDaily(appUser, daily, picture);
        appUserDailyRepository.save(appUserDaily);

        appUser.addPoint();
        appUserRepository.save(appUser);

        boolean isBothUploaded = utilService.isBothUploaded(mateId);
        if (isBothUploaded) {
            daily.updateIsBothUploaded();
            dailyRepository.save(daily);

            mate.addDailyContinuousDays();
            mateRepository.save(mate);
        }
    }

    // 일일 조회 메소드
    public DailyResponse getTodayDaily(Long mateId) {

        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        LocalDate targetDate = LocalDate.now(); // 오늘의 날짜와 시간을 얻습니다.
        LocalDateTime startDateTime = targetDate.atStartOfDay();
        LocalDateTime endDateTime = targetDate.atTime(23, 59, 59);
        Daily daily = dailyRepository.findByMateAndCreateDateBetween(mate, startDateTime, endDateTime).
                orElseThrow(() -> new IllegalArgumentException("해당 일일이 없습니다."));

        List<AppUserDailyResponse> appUserDailyResponseList = daily.getAppUserDailyList().stream()
                .map(appUserDaily -> new AppUserDailyResponse(appUserDaily.getId(), appUserDaily.getPicture()))
                .toList();

        return new DailyResponse(
                daily.getId()
                , daily.getMate()
                , daily.getTag()
                , daily.getIsBothUploaded()
                , appUserDailyResponseList
        );
    }

    // 특정 날짜 데일리 조회 메소드
    public DailyResponse getSpecificDaily(Long mateId, LocalDate targetDate) {

        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        LocalDateTime startDateTime = targetDate.atStartOfDay();
        LocalDateTime endDateTime = targetDate.atTime(23, 59, 59);
        Daily daily = dailyRepository.findByMateAndCreateDateBetween(mate, startDateTime, endDateTime).
                orElseThrow(() -> new IllegalArgumentException("해당 일일이 없습니다."));

        List<AppUserDailyResponse> appUserDailyResponseList = daily.getAppUserDailyList().stream()
                .map(appUserDaily -> new AppUserDailyResponse(appUserDaily.getId(), appUserDaily.getPicture()))
                .toList();

        return new DailyResponse(
                daily.getId()
                , daily.getMate()
                , daily.getTag()
                , daily.getIsBothUploaded()
                , appUserDailyResponseList
        );
    }
}
