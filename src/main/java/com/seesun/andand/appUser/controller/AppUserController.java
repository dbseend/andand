package com.seesun.andand.appUser.controller;

import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUser.service.AppUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    // 회원정보 조회 API

    @GetMapping("")
    @Operation(summary = "회원정보 조회", description = "회원정보 조회 API")
    public ResponseEntity<AppUserResponse> getAppUser(@RequestParam String userId) {

        return ResponseEntity.ok(appUserService.getAppUser(userId));
    }

    // 회원정보 수정 API
    @PatchMapping("")
    @Operation(summary = "회원정보 수정", description = "회원정보 수정 API")
    public ResponseEntity<AppUserResponse> updateAppUser(@ModelAttribute AppUserUpdateRequest appUserUpdateRequest) throws Exception {

        return ResponseEntity.ok(appUserService.updateAppUser(appUserUpdateRequest));
    }
}
