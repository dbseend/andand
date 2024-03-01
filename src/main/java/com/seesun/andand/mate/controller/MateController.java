package com.seesun.andand.mate.controller;

import com.seesun.andand.mate.dto.request.ConnectMateRequest;
import com.seesun.andand.mate.service.MateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/mate")
@RequiredArgsConstructor
public class MateController {

    private final MateService mateService;

    // 메이트 연결 API
    @PostMapping("")
    @Operation(summary = "메이트 연결", description = "메이트 연결 API")
    public ResponseEntity<Void> connectMate(@RequestBody ConnectMateRequest connectMateRequest) {
        mateService.connectMate(connectMateRequest);
        return ResponseEntity.ok().build();
    }

    // 메이트 연결 해제 API
    @DeleteMapping("")
    @Operation(summary = "메이트 연결 해제", description = "메이트 연결 해제 API")
    public ResponseEntity<Void> disconnectMate(@RequestParam String userId) {
        mateService.disconnectMate(userId);
        return ResponseEntity.ok().build();
    }
}
