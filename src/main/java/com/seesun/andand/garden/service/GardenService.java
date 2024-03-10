package com.seesun.andand.garden.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.garden.domain.Garden;
import com.seesun.andand.garden.dto.request.GardenUpdateRequest;
import com.seesun.andand.garden.dto.request.GardenUploadRequest;
import com.seesun.andand.garden.dto.response.GardenAllResponse;
import com.seesun.andand.garden.dto.response.GardenResponse;
import com.seesun.andand.util.UtilService;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.garden.domain.GardenRepository;
import com.seesun.andand.garden.dto.response.GardenInfo;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class GardenService {

    private static final String GARDEN = "garden";
    private final GardenRepository gardenRepository;
    private final MateRepository mateRepository;
    private final AppUserRepository appUserRepository;
    private final UtilService utilService;

    public GardenInfo getGardenInfo(Long mateId) {
        Mate mate = mateRepository.findById(mateId).orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        Integer gardenLevel = mate.getGardenLevel();

        Integer gardenNum = mate.getGardenNum();

        List<AppUserMate> appUserMates = mate.getAppUserMateList();

        List<String> appUserNameList = appUserMates.stream().map(appUserMate -> appUserMate.getAppUser().getName()).toList();

        return new GardenInfo(gardenLevel, gardenNum, appUserNameList);
    }

    //garden 게시물 업로드
    public GardenResponse uploadGarden(GardenUploadRequest gardenUploadRequest) throws IOException {

        AppUser appUser = appUserRepository.findById(String.valueOf(gardenUploadRequest.getAppUserId()))
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Mate mate = mateRepository.findById(gardenUploadRequest.getMateId())
                .orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        //만약 appUser가 이미 오늘 안에 garden 게시물을 올린 것이 있다면 예외 처리
        List<Garden> gardenList = gardenRepository.findByAppUser(appUser);
        for (Garden garden : gardenList) {
            if (isUploadedToday(garden)) {
                throw new IllegalArgumentException("오늘 이미 게시물을 올렸습니다.");
            }
        }

        String picture = utilService.uploadImage(gardenUploadRequest.getPicture(), GARDEN);

        Garden garden = Garden.builder()
                .appUser(appUser)
                .mate(mate)
                .image(picture)
                .content(gardenUploadRequest.getContent())
                .build();

        gardenRepository.save(garden);

        mate.setGardenNum(mate.getGardenNum() + 1);
        mate.setGardenLevel();
        mateRepository.save(mate);

        return new GardenResponse(garden);
    }

    //garden id 를 이용해서 garden 게시물 하나 조회하기
    public GardenResponse getGarden(Long gardenId) {
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. Garden ID: " + gardenId));

        return new GardenResponse(garden);
    }

    // garden 게시물 리스트 조회하기
    public GardenAllResponse getGardenAll() {

        List<Garden> gardenList = gardenRepository.findAll();

        List<GardenResponse> gardenResponseList = gardenList.stream()
                .map(GardenResponse::new)
                .toList();

        return new GardenAllResponse(gardenResponseList, gardenResponseList.size());
    }

    //garden id를 통해 게시물 수정하기
    public GardenResponse updateGarden(Long gardenId, GardenUpdateRequest request) throws IOException {

        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        garden.update(request.getContent(), request.getPicture());
        gardenRepository.save(garden);

        return new GardenResponse(garden);
    }

    //garden 게시물 삭제
    public void deleteGarden(Long gardenId) {

        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        Mate mate = garden.getMate();
        mate.setGardenNum(mate.getGardenNum() - 1);
        mateRepository.save(mate);

        gardenRepository.deleteById(gardenId);
    }

    // 수정된 isUploadedToday 메서드
    public boolean isUploadedToday(Garden garden) {
        if (garden == null || garden.getCreateDate() == null) {
            return false;
        }

        LocalDateTime todayStart = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime todayEnd = LocalDateTime.now().toLocalDate().atTime(23, 59, 59, 999999999);

        return !garden.getCreateDate().isBefore(todayStart) && !garden.getCreateDate().isAfter(todayEnd);
    }

    // 오늘 하루 안에 올렸던 게시물 중 가장 최근 게시물 조회
    public GardenResponse getTodayGarden() {
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        // 오늘 게시된 게시물 중 가장 최근의 게시물 가져오기
        Optional<Garden> latestGarden = gardenRepository.findTopByCreateDateBetweenOrderByCreateDateDesc(
                todayStart, todayEnd);

        return latestGarden.map(GardenResponse::new)
                .orElse(new GardenResponse()); // 없으면 빈 응답 또는 예외 처리 등을 수행할 수 있습니다.
    }

}
