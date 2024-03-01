package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    @Schema(description = "회원 ID", example = "1", required = true)
    private String userId;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @Schema(description = "프로필 이미지", required = true)
    private String profileImage;

    @Schema(description = "유저 코드", example = "1234", required = true)
    private String userCode;

    public SignUpResponse(AppUser appUser) {
        this.userId = appUser.getUserId();
        this.name = appUser.getName();
        this.profileImage = appUser.getProfileImage();
        this.userCode = appUser.getUserCode();
    }
}
