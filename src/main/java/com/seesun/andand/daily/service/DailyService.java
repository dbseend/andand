package com.seesun.andand.daily.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserDaily.domain.AppUserDailyRepository;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
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

    @Scheduled(cron = "0 49 3 * * ?") // 매일 자정에 실행
    public void everyDay() {

        log.info("자동 실행되었습니다.");

        List<Mate> mateList = mateRepository.findAll();
        List<Daily> dailyList = mateList.stream()
                .map(mate -> new Daily("웃음", mate))
                .toList();

        dailyRepository.saveAll(dailyList);
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
    }
}
