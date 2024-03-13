package com.seesun.andand.daily.controller;

import com.seesun.andand.daily.dto.response.DailyInfoResponse;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.daily.service.DailyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v0/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;

    // 일상 게시물 업로드 API
    @PostMapping("")
    @Operation(summary = "일상 게시물 업로드", description = "일상 게시물 업로드 API")
    public ResponseEntity<Void> uploadDaily(@RequestParam String appUserId, @RequestParam Long mateId, @RequestParam MultipartFile file) throws IOException {

        dailyService.uploadDaily(appUserId, mateId, file);

        return ResponseEntity.ok().build();
    }

    // 일상 게시물 재업로드 API
    @PostMapping("/reUpload")
    @Operation(summary = "일상 게시물 재업로드", description = "일상 게시물 재업로드 API")
    public ResponseEntity<Void> reUploadDaily(@RequestParam String appUserId, @RequestParam MultipartFile file) throws IOException {

        dailyService.reUploadDaily(appUserId, file);

        return ResponseEntity.ok().build();
    }

    // 일상 게시물 삭제 API
    @GetMapping("/today")
    @Operation(summary = "오늘의 일상 게시물 조회", description = "오늘의 일상 게시물 조회 API")
    public ResponseEntity<DailyResponse> getTodayDaily(@RequestParam Long mateId) {

        DailyResponse dailyResponse = dailyService.getTodayDaily(mateId);

        return ResponseEntity.ok().body(dailyResponse);
    }

    // 특정 일의 일상 게시물 조회 API
    @GetMapping("")
    @Operation(summary = "특정 일의 일상 게시물 조회", description = "특정 일의 일상 게시물 조회 API")
    public ResponseEntity<DailyResponse> getSpecificDaily(@RequestParam Long mateId, @RequestParam LocalDate date) {

        DailyResponse dailyCheckResponse = dailyService.getSpecificDaily(mateId, date);

        return ResponseEntity.ok().body(dailyCheckResponse);
    }

    // 일상 정보 조회 API
    @GetMapping("/info")
    @Operation(summary = "일상 정보 조회", description = "일상 정보 조회 API")
    public ResponseEntity<DailyInfoResponse> getDailyInfo(@RequestParam String userId) {

        DailyInfoResponse dailyInfoResponse = dailyService.getDailyInfo(userId);

        return ResponseEntity.ok().body(dailyInfoResponse);
    }
}
