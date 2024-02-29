package com.seesun.andand.daily.controller;

import com.seesun.andand.daily.dto.response.DailyInfoResponse;
import com.seesun.andand.daily.dto.response.DailyResponse;
import com.seesun.andand.daily.service.DailyService;
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

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadDaily(@RequestParam Long appUserId, @RequestParam Long mateId, @RequestParam MultipartFile file) throws IOException {

        dailyService.uploadDaily(appUserId, mateId, file);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/reUpload")
    public ResponseEntity<Void> reUploadDaily(@RequestParam Long appUserId, @RequestParam MultipartFile file) throws IOException {

        dailyService.reUploadDaily(appUserId, file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/today")
    public ResponseEntity<DailyResponse> getTodayDaily(@RequestParam Long mateId) {

        DailyResponse dailyResponse = dailyService.getTodayDaily(mateId);

        return ResponseEntity.ok().body(dailyResponse);
    }

    @GetMapping("")
    public ResponseEntity<DailyResponse> getSpecificDaily(@RequestParam Long mateId, @RequestParam LocalDate date) {

        DailyResponse dailyResponse = dailyService.getSpecificDaily(mateId, date);

        return ResponseEntity.ok().body(dailyResponse);
    }


    @GetMapping("/info")
    public ResponseEntity<DailyInfoResponse> getDailyInfo(@RequestParam String userId) {

        DailyInfoResponse dailyInfoResponse = dailyService.getDailyInfo(userId);

        return ResponseEntity.ok().body(dailyInfoResponse);
    }
}
