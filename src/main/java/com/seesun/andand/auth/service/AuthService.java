package com.seesun.andand.auth.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import com.seesun.andand.auth.dto.request.SignInRequest;
import com.seesun.andand.auth.dto.request.SignUpRequest;
import com.seesun.andand.auth.dto.response.SignUpResponse;
import com.seesun.andand.auth.dto.response.SignInResponse;
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

    // 회원가입 메소드(아이디 중복 체크 + 프로필 사진 업로드 + 유저 코드 생성 + 객체 생성)
    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) throws Exception {

        authSubService.checkIdAvailability(signUpRequest.getUserId());
        String profileImage = utilService.uploadImage(signUpRequest.getProfileImage(), PROFILE_DIRECTORY);
        String userCode = utilService.generateRandomCode();
        AppUser appUser = appUserRepository.save(signUpRequest.toEntity(userCode, profileImage));

        return new SignUpResponse(appUser);
    }

    // 로그인 메소드(아이디 비밀번호 확인, 토큰 생성)
    @Transactional
    public SignInResponse signIn(SignInRequest signInRequest) {

        AppUser appUser = authSubService.findUserByUserId(signInRequest.getUserId());
        if (!appUser.getPassword().equals(signInRequest.getPassword())) { // 비밀번호가 일치하지 않는 경우
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = tokenProvider.create(appUser.getUserId());
        appUser.updateToken(token);

        appUserRepository.save(appUser);

        return new SignInResponse(appUser.getUserId(), token);
    }

    // 로그아웃 메소드(토큰 삭제)
    @Transactional
    public void signOut(String userId) {
        AppUser appUser = appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        appUser.updateToken(null);
    }
}
