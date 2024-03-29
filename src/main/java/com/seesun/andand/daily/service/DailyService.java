package com.seesun.andand.daily.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUser.service.AppUserService;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserDaily.domain.AppUserDailyRepository;
import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.daily.dto.response.DailyInfoResponse;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.daily.dto.response.DailyInfo;
import com.seesun.andand.exception.ErrorCode;
import com.seesun.andand.exception.MyException;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//enetity -> repo -> service -> controller ->api호출
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
    private final AppUserService appUserService;
    private final AppUserSubService appUserSubService;


    // 일일 등록 메소드
    public void uploadDaily(String appUserId, Long mateId, MultipartFile file) throws IOException {

        LocalDate today = LocalDate.now();
        String date = today.toString();

        AppUser appUser = appUserSubService.findAppUserById(appUserId);
        DailyInfo dailyInfo = utilService.getInfoForDaily(mateId, today);
        Daily daily = dailyInfo.getDaily();
        Mate mate = dailyInfo.getMate();

        String picture = utilService.uploadImage(file, DAILY, appUserId, date);

        // Daily 엔티티를 먼저 저장합니다.
        dailyRepository.save(daily);

        // AppUserDaily 엔티티를 생성하고, Daily 엔티티의 참조를 설정합니다.
        AppUserDaily appUserDaily = Optional.ofNullable(new AppUserDaily(appUser, daily, picture))
                .orElseThrow(() -> new IllegalArgumentException("AppUserDaily 객체를 생성할 수 없습니다."));

        // AppUserDaily 엔티티를 저장합니다.
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


    public void reUploadDaily(String appUserId, MultipartFile file) throws IOException {

        LocalDate today = LocalDate.now();
        String date = today.toString();

        LocalDateTime startDateTime = today.atStartOfDay();
        LocalDateTime endDateTime = today.atTime(23, 59, 59);
        AppUserDaily appUserDaily = new AppUserDaily();

        String picture = utilService.uploadImage(file, DAILY, appUserId, date);
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

    // 데일리 게시물 조회 메소드 - 하루
    private DailyResponse getDailyResponse(Long mateId, LocalDate today) {
        DailyInfo dailyInfo = utilService.getInfoForDaily(mateId, today);
        Daily daily = dailyInfo.getDaily();

        List<AppUserDailyResponse> appUserDailyResponseList = daily.getAppUserDailyList().stream()
                .map(appUserDaily -> new AppUserDailyResponse(appUserDaily.getId(), appUserDaily.getPicture()))
                .toList();

        return new DailyResponse(
                daily.getId()
                , daily.getTag()
                , daily.getIsBothUploaded()
                , appUserDailyResponseList
        );
    }

    //사용자 조회
    public DailyInfoResponse getDailyInfo(String userId) {

        AppUserResponse appUserResponse = appUserService.getAppUser(userId);

//        AppUser appUser = appUserRepository.findById(appUserResponse.getId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        AppUser appUser = new AppUser();

        List<Mate> filteredMates = appUser.getAppUserMateList().stream()
                .map(AppUserMate::getMate)
                .filter(mate -> mate.getAppUserMateList().size() == 2)
                .peek(mate -> log.info("mate.getAppUserMateList().size() : " + mate.getAppUserMateList().size()))
                .toList();

        Integer dailyContinuousDays = filteredMates.get(0).getDailyContinuousDays();

        String dailyTag = "웃음";

        return new DailyInfoResponse(appUserResponse, dailyContinuousDays, dailyTag);
    }

}
