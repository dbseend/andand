package com.seesun.andand.mate.controller;

import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.service.MateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/mate")
@RequiredArgsConstructor
public class MateController {

    private final MateService mateService;

    @PostMapping("")
    public ResponseEntity<Void> connectMate(@RequestBody ConnectMateRequest connectMateRequest) {
        mateService.connectMate(connectMateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/appUser/{appUserId}/mate/{mateId}")
    public ResponseEntity<Void> disconnectMate(Long appUserId, Long mateId) {
        mateService.disconnectMate(appUserId, mateId);
        return ResponseEntity.ok().build();
    }
}
