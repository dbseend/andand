package com.seesun.andand.daily.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserDaily.domain.AppUserDailyRepository;
import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.daily.dto.response.DailyInfo;
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

    // 데일리 자동 생성 및 연속 날짜 갱신 메소드
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void everyDay() {

        LocalDate yesterday = LocalDate.now().minusDays(1); // 어제의 날짜를 얻습니다.
        LocalDateTime startDateTime = yesterday.atStartOfDay();
        LocalDateTime endDateTime = yesterday.atTime(23, 59, 59);
        List<Mate> mateList = mateRepository.findAll();

        // 어제 일일 등록이 안된 메이트들의 연속일을 초기화합니다. -> 수정필요
//        List<Daily> yesterdayDailyList = dailyRepository.findByCreateDateBetween(startDateTime, endDateTime);
//        for (Daily daily : yesterdayDailyList) {
//            if (!daily.getIsBothUploaded()) {
//                daily.getMate().initDailyContinuousDays();
//                mateRepository.save(daily.getMate());
//            }
//        }

        List<Daily> todayDailyList = mateList.stream()
                .map(mate -> new Daily("웃음", mate, false))
                .toList();

        dailyRepository.saveAll(todayDailyList);
    }

    // 일일 등록 메소드
    public void uploadDaily(Long appUserId, Long mateId, MultipartFile file) throws IOException {

        AppUser appUser = appUserRepository.findById(appUserId).
                orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        LocalDate today = LocalDate.now();
        DailyInfo dailyInfo = utilService.getInfoForDaily(mateId, today);
        Daily daily = dailyInfo.getDaily();
        Mate mate = dailyInfo.getMate();

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

    public void reUploadDaily(Long appUserId, MultipartFile file) throws IOException {

        LocalDate today = LocalDate.now();
        LocalDateTime startDateTime = today.atStartOfDay();
        LocalDateTime endDateTime = today.atTime(23, 59, 59);
        AppUserDaily appUserDaily = new AppUserDaily();
//        AppUserDaily appUserDaily = appUserDailyRepository.findByAppUserIdAndCreateDateTimeBetween(appUserId, startDateTime, endDateTime)
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 데일리가 없습니다."));

        String picture = utilService.uploadImage(file, DAILY);
        appUserDaily.updatePicture(picture);
        appUserDailyRepository.save(appUserDaily);
    }

    // 일일 데일리 게시물 조회 메소드
    public DailyResponse getTodayDaily(Long mateId) {

        LocalDate today = LocalDate.now();

        return getDailyResponse(mateId, today);
    }

    // 특정 날짜 데일리 조회 메소드
    public DailyResponse getSpecificDaily(Long mateId, LocalDate targetDate) {
        return getDailyResponse(mateId, targetDate);
    }

    // 데일리 게시물 조회 메소드
    private DailyResponse getDailyResponse(Long mateId, LocalDate today) {
        DailyInfo dailyInfo = utilService.getInfoForDaily(mateId, today);
        Daily daily = dailyInfo.getDaily();

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
