package com.seesun.andand.garden.Controller;

import com.seesun.andand.garden.dto.request.GardenUpdateRequest;
import com.seesun.andand.garden.dto.request.GardenUploadRequest;
import com.seesun.andand.garden.dto.response.GardenAllResponse;
import com.seesun.andand.garden.dto.response.GardenInfo;
import com.seesun.andand.garden.dto.response.GardenResponse;
import com.seesun.andand.garden.service.GardenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/garden")
public class GardenController {

    private final GardenService gardenService;

    @GetMapping("/{mateId}")
    @Operation(summary = "정원 정보 조회", description = "정원 정보 조회 API")
    public ResponseEntity<GardenInfo> getGardenInfo(@PathVariable Long mateId) {
        return ResponseEntity.ok(gardenService.getGardenInfo(mateId));
    }

    //garden 게시물 조회
    @GetMapping("/post/{gardenId}")
    public ResponseEntity<GardenResponse> getGarden(@PathVariable Long gardenId) {
        return ResponseEntity.ok(gardenService.getGarden(gardenId));
    }

    //garden 전체 게시물 조회
    @GetMapping("")
    public ResponseEntity<GardenAllResponse> getGardenAll() {
        return ResponseEntity.ok(gardenService.getGardenAll());
    }

    //garden 게시물 업로드
    @PostMapping("")
    public ResponseEntity<GardenResponse> uploadGarden(
            @RequestParam("appUserId") Long appUserId,
            @RequestParam("mateId") Long mateId,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("content") String content) throws IOException {

        GardenUploadRequest gardenUploadRequest = new GardenUploadRequest(appUserId, mateId, picture, content);

        return ResponseEntity.ok(gardenService.uploadGarden(gardenUploadRequest));
    }

    //garden 게시물 수정
    @PatchMapping("/{gardenId}")
    public ResponseEntity<GardenResponse> updateGarden(@PathVariable Long gardenId, @RequestParam("picture") MultipartFile picture, @RequestParam("content") String content) throws IOException {
        GardenUpdateRequest gardenUpdateRequest = new GardenUpdateRequest(content, picture);
        return ResponseEntity.ok(gardenService.updateGarden(gardenId, gardenUpdateRequest));
    }

    //garden 게시물 삭제
    @DeleteMapping("/{gardenId}")
    public ResponseEntity<Void> deleteGarden(@PathVariable Long gardenId) {
        gardenService.deleteGarden(gardenId);
        return ResponseEntity.ok().build();
    }

    //오늘 올린 게시물 중 가장 최근 게시물 조회
    @GetMapping("/today")
    public ResponseEntity<GardenResponse> getTodayGarden() {
        return ResponseEntity.ok(gardenService.getTodayGarden());
    }
}
