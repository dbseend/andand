package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    @Schema(description = "회원 ID", example = "1", required = true)
    private String userId;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @Schema(description = "나이", example = "20", required = true)
    private Integer age;

    @Schema(description = "성별", example = "M", required = true)
    private String phoneNumber;

    @Schema(description = "유저 코드", example = "1234", required = true)
    private String userCode;

    @Schema(description = "프로필 이미지", required = true)
    private String profileImage;

    public AppUserResponse(AppUser appUser) {
        this.userId = appUser.getUserId();
        this.profileImage = appUser.getProfileImage();
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
        this.userCode = appUser.getUserCode();
    }
}
