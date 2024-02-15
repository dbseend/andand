package com.seesun.andand.appUserDaily.dto;

import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserDailyResponse {

    private Long id;

    private String picture;

    public AppUserDailyResponse(Long id, String picture) {
        this.id = id;
        this.picture = picture;
    }
}
