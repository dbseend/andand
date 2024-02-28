package com.seesun.andand.garden.service;

import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.garden.domain.GardenRepository;
import com.seesun.andand.garden.dto.response.GardenInfo;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GardenService {
    private final MateRepository mateRepository;

    public GardenInfo getGardenInfo(Long mateId) {
        Mate mate = mateRepository.findById(mateId).orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));
        Integer gardenLevel = mate.getGardenLevel();

        Integer gardenNum = mate.getGardenNum();

        List<AppUserMate> appUserMates = mate.getAppUserMateList();

        List<String> appUserNameList = appUserMates.stream().map(appUserMate -> appUserMate.getAppUser().getName()).toList();

        return new GardenInfo(gardenLevel, gardenNum, appUserNameList);
    }
}
