package com.seesun.andand.appUser.service;

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
public class AppUserSubService {

    private final AppUserRepository appUserRepository;
    private final AppUserMateRepository appUserMateRepository;
    private final MateRepository mateRepository;

    public AppUser findAppUserById(Long userId) {
        return appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }

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
                .orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));
    }

    public Mate findMateById(Long mateId) {
        return mateRepository.findById(mateId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));
    }
}
