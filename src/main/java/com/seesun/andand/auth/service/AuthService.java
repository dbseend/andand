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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final UtilService utilService;
    private final TokenProvider tokenProvider;

    // 회원가입 메소드
    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        boolean isExist = appUserRepository.existsByUserId(signUpRequest.getUserId());
        if (isExist) { // 이미 존재하는 아이디인 경우
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String userCode = utilService.generateRandomCode();

        AppUser appUser = appUserRepository.save(signUpRequest.toEntity(userCode));

        return new SignUpResponse(appUser);
    }

    // 로그인 메소드
    @Transactional
    public SignInResponse signIn(SignInRequest signInRequest) {

        AppUser appUser = appUserRepository.findByUserId(signInRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다.")); // 존재하지 않는 아이디인 경우

        if (!appUser.getPassword().equals(signInRequest.getPassword())) { // 비밀번호가 일치하지 않는 경우
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        int exprTime = 1000 * 3600 * 3;
        String token = tokenProvider.create(appUser.getUserId());
        appUser.updateToken(token);
        appUserRepository.save(appUser);

        return new SignInResponse(appUser.getUserId(), token);
    }

    // 로그아웃 메소드
    @Transactional
    public void signOut(String userId) {
        AppUser appUser = appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
        appUser.updateToken(null);
    }
}
