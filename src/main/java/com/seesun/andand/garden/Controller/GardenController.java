package com.seesun.andand.garden.Controller;

import com.seesun.andand.garden.dto.response.GardenInfo;
import com.seesun.andand.garden.service.GardenService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/garden")
public class GardenController {

    private final GardenService gardenService;

    @GetMapping("/{mateId}")
    @ApiOperation(value = "정원 정보 조회", notes = "정원 정보 조회 API")
    public ResponseEntity<GardenInfo> getGardenInfo(@PathVariable Long mateId) {
        return ResponseEntity.ok(gardenService.getGardenInfo(mateId));
    }

}
