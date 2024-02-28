package com.seesun.andand.mate.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserMate.domain.AppUserMateRepository;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MateSubService {

    private final MateRepository mateRepository;
    private final AppUserMateRepository appUserMateRepository;

    // 메이트 등록 메소드
    public void connectMate(AppUser appUser) {

        Mate mate = new Mate(appUser.getUserCode());
        mateRepository.save(mate);

        AppUserMate appUserMate = new AppUserMate(appUser, mate);
        appUserMateRepository.save(appUserMate);
    }

}
