package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserResponse;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "사용자 정보", required = true)
    private AppUserResponse appUserResponse;

    @ApiModelProperty(value = "연속 일수", required = true)
    private Integer continuousDays;

    @ApiModelProperty(value = "오늘의 태그", required = true)
    private String todayTag;
}
