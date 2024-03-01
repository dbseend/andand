package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserResponse;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class DailyInfoResponse {
    //- **사용자 정보 조회**
    //- **연속 일수 조회**
    //- **오늘의 태그 조회**

    @Schema(description = "사용자 정보", required = true)
    private AppUserResponse appUserResponse;

    @Schema(description = "연속 일수", required = true)
    private Integer continuousDays;

    @Schema(description = "오늘의 태그", required = true)
    private String todayTag;
}
