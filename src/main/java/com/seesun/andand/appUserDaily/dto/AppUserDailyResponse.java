package com.seesun.andand.appUserDaily.dto;

import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserDailyResponse {

    @Schema(description = "회원 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "프로필 이미지", required = true)
    private String picture;

    public AppUserDailyResponse(Long id, String picture) {
        this.id = id;
        this.picture = picture;
    }
}
