package com.seesun.andand.appUser.controller;

import com.seesun.andand.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.appUser.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/appUser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    // 회원정보 조회 API
    @GetMapping("/{appUserId}")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable Long appUserId) {

        return ResponseEntity.ok(appUserService.getAppUser(appUserId));
    }

    // 회원정보 수정 API
    @PatchMapping("/{appUserId}")
    public ResponseEntity<AppUserResponse> updateAppUser(@RequestBody AppUserUpdateRequest appUserUpdateRequest) {

        return ResponseEntity.ok(appUserService.updateAppUser(appUserUpdateRequest));
    }
}
