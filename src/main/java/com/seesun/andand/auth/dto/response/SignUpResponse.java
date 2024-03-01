package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    @ApiModelProperty(value = "회원 ID", required = true)
    private String userId;

    @ApiModelProperty(value = "이름", required = true)
    private String name;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private String profileImage;

    @ApiModelProperty(value = "유저 코드", required = true)
    private String userCode;

    public SignUpResponse(AppUser appUser) {
        this.userId = appUser.getUserId();
        this.name = appUser.getName();
        this.profileImage = appUser.getProfileImage();
        this.userCode = appUser.getUserCode();
    }
}
