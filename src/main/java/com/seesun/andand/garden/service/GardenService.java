package com.seesun.andand.garden.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUserGarden.domain.AppUserGarden;
import com.seesun.andand.appUserGarden.domain.AppUserGardenRepository;
import com.seesun.andand.appUserGarden.dto.AppUserGardenResponse;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.garden.domain.Garden;
import com.seesun.andand.garden.dto.response.GardenInfo;
import com.seesun.andand.garden.dto.response.GardenResponse;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.util.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GardenService {

    private static final String GARDEN = "garden";
    private final AppUserRepository appUserRepository;
    private final MateRepository mateRepository;
    private final AppUserGardenRepository appUserGardenRepository;
    private final UtilService utilService;

    // 정원 게시물 업로드 메소드
    public void uploadGarden(Long appUserId, Long mateId, MultipartFile file, String content) throws IOException {

        AppUser appUser = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        LocalDate today = LocalDate.now();
        GardenInfo gardenInfo = utilService.getInfoForGarden(mateId, today);
        Garden garden = gardenInfo.getGarden();

        String picture = utilService.uploadImage(file, GARDEN);

        AppUserGarden appUserGarden = AppUserGarden.builder()
                .appUser(appUser)
                .garden(garden)
                .picture(picture)
                .content(content)
                .build();
        appUserGardenRepository.save(appUserGarden);

        Mate mate = gardenInfo.getMate();
        mate.addGardenNum();
        mateRepository.save(mate);
    }

    // 일일 정원 게시물 조회 메소드
    public GardenResponse getTodayGarden(Long mateId) {

        LocalDate today = LocalDate.now();

        return getGardenResponse(mateId, today);
    }

    // 특정 날짜 정원 조회 메소드
    public GardenResponse getSpecificDaily(Long mateId, LocalDate targetDate) {
        return getGardenResponse(mateId, targetDate);
    }

    // 정원 게시물 조회 메소드
    public GardenResponse getGardenResponse(Long appUserId, LocalDate date) {

        GardenInfo gardenInfo = utilService.getInfoForGarden(appUserId, date);
        Garden garden = gardenInfo.getGarden();
        Mate mate = gardenInfo.getMate();

        List<AppUserGardenResponse> appUserGardenResponseList = garden.getAppUserGardenList().stream()
                .map(AppUserGardenResponse::new)
                .toList();

        return new GardenResponse(garden.getId(), mate, appUserGardenResponseList);
    }
}
