package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserResponse;
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

    private AppUserResponse appUserResponse;

    private Integer continuousDays;

    private String todayTag;


}
