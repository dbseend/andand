package com.seesun.andand.appUser.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import com.seesun.andand.mate.service.MateSubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserSubService {

    private final AppUserRepository appUserRepository;
    private final MateSubService mateSubService;

    // id로 메이트 조회 메소드
    public AppUser findAppUserById(String userId) {
        return appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }

    // 메이트 코드로 파트너 조회 메소드
    public PartnerResponse findPartnerInfoByMateCode(String mateCode) {

        Mate mate = mateSubService.findMateByCode(mateCode);

        if (mate.getAppUserMateList().size() == 2) {
            throw new IllegalArgumentException("이미 메이트가 연결되어 있습니다.");
        }

        AppUser appUser = mate.getAppUserMateList().get(0).getAppUser();

        return new PartnerResponse(appUser);
    }

    // 유저 아이디로 파트너 정보 조회 메소드
    public PartnerResponse findPartnerInfoByUserId(String userId) {

        AppUser appUser = findAppUserById(userId);
        List<AppUserMate> appUserMateList = appUser.getAppUserMateList();
        Optional<Mate> mate = appUserMateList.stream()
                .map(AppUserMate::getMate)
                .filter(appUserMateMate -> appUserMateMate.getAppUserMateList().size() == 2)
                .findFirst();

        AppUser partner = mate.get().getAppUserMateList().stream()
                .filter(appUserMate -> !appUserMate.getAppUser().getUserId().equals(userId))
                .findFirst()
                .get()
                .getAppUser();

        return new PartnerResponse(partner);
    }
}
