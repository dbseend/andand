package com.seesun.andand.daily.controller;

import com.seesun.andand.daily.dto.request.DailyUploadRequest;
import com.seesun.andand.daily.service.DailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v0/daily")
@RequiredArgsConstructor
public class DailyController {

    private final DailyService dailyService;

    @PostMapping("")
    public ResponseEntity<Void> uploadDaily(@RequestParam Long appUserId, @RequestParam Long mateId, @RequestParam MultipartFile file) throws IOException {

        dailyService.uploadDaily(appUserId, mateId, file);

        return ResponseEntity.ok().build();
    }
}
