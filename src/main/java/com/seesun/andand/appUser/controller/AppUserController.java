package com.seesun.andand.appUser.controller;

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

//    @PostMapping("/signUp")
//    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
//
//        appUserService.signUp(signUpRequest);
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/{appUserId}")
    public ResponseEntity<AppUserResponse> getAppUser(@PathVariable Long appUserId) {

        return ResponseEntity.ok(appUserService.getAppUser(appUserId));
    }
}
