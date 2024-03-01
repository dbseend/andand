package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserSummaryResponse {

    @ApiModelProperty(value = "이름", required = true)
    private String userName;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private String profileImage;

    public AppUserSummaryResponse(AppUser appUser) {
        this.userName = appUser.getName();
        this.profileImage = appUser.getProfileImage();
    }
}
