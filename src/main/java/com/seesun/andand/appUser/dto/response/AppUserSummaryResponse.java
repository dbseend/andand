package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserSummaryResponse {

    @Schema(description = "이름", example = "홍길동", required = true)
    private String userName;

    @Schema(description = "프로필 이미지", required = true)
    private String profileImage;

    public AppUserSummaryResponse(AppUser appUser) {
        this.userName = appUser.getName();
        this.profileImage = appUser.getProfileImage();
    }
}
