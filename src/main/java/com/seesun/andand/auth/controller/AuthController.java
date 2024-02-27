package com.seesun.andand.auth.controller;

import com.seesun.andand.auth.dto.request.SignInRequest;
import com.seesun.andand.auth.dto.request.SignUpRequest;
import com.seesun.andand.auth.dto.response.SignInResponse;
import com.seesun.andand.auth.dto.response.SignUpResponse;
import com.seesun.andand.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입 API
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@ModelAttribute SignUpRequest signUpRequest) throws Exception {
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    // 로그인 API
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    // 로그아웃 API
    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(@RequestParam String userId) {
        authService.signOut(userId);
        return ResponseEntity.ok().build();
    }
}
