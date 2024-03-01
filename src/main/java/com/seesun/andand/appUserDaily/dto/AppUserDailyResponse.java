package com.seesun.andand.appUserDaily.dto;

import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserDailyResponse {

    @ApiModelProperty(value = "회원 ID", required = true)
    private Long id;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private String picture;

    public AppUserDailyResponse(Long id, String picture) {
        this.id = id;
        this.picture = picture;
    }
}
