package com.seesun.andand.auth.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.appUser.service.AppUserSubService;
import com.seesun.andand.auth.dto.request.SignInRequest;
import com.seesun.andand.auth.dto.request.SignUpRequest;
import com.seesun.andand.auth.dto.response.SignUpResponse;
import com.seesun.andand.auth.dto.response.SignInResponse;
import com.seesun.andand.mate.dto.response.MateResponse;
import com.seesun.andand.mate.service.MateService;
import com.seesun.andand.mate.service.MateSubService;
import com.seesun.andand.util.UtilService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private static final String PROFILE_DIRECTORY = "profile";
    private final AppUserRepository appUserRepository;
    private final UtilService utilService;
    private final TokenProvider tokenProvider;
    private final AuthSubService authSubService;
    private final MateSubService mateSubService;
    private final AppUserSubService appUserSubService;
    private final MateService mateService;

    // 회원가입 메소드
    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws Exception {

        authSubService.checkIdAvailability(signUpRequest.getUserId()); // 아이디 중복 체크
        String profileImage = utilService.uploadImage(signUpRequest.getProfileImage(), PROFILE_DIRECTORY); // 프로필 이미지 업로드
        String userCode = utilService.generateRandomCode(); // 유저 코드 생성
        AppUser appUser = appUserRepository.save(signUpRequest.toEntity(userCode, profileImage)); // 사용자 저장
        mateSubService.createMate(appUser); // 사용자의 메이트 생성

        return new SignUpResponse(appUser);
    }

    // 로그인 메소드
    @Transactional
    public SignInResponse signIn(SignInRequest signInRequest) {

        AppUser appUser = authSubService.findUserByUserId(signInRequest.getUserId()); // 아이디로 사용자 조회
        if (!appUser.getPassword().equals(signInRequest.getPassword())) { // 비밀번호가 일치하지 않는 경우
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        AppUserSummaryResponse appUserSummaryResponse = new AppUserSummaryResponse(appUser);

        PartnerResponse partnerResponse = appUserSubService.findPartnerInfoByUserId(appUser.getUserId()); // 파트너 정보 조회

        MateResponse mateResponse = mateService.findMate(appUser.getUserId()); // 메이트 정보 조회

        String token = tokenProvider.create(appUser.getUserId()); // 토큰 생성
        appUser.updateToken(token);
        appUserRepository.save(appUser);

        return new SignInResponse(appUserSummaryResponse, partnerResponse, mateResponse, token);
    }

    // 로그아웃 메소드
    @Transactional
    public void signOut(String userId) {
        AppUser appUser = appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        appUser.updateToken(null);
    }
}