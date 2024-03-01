package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    @ApiModelProperty(value = "회원 ID", required = true)
    private String userId;

    @ApiModelProperty(value = "이름", required = true)
    private String name;

    @ApiModelProperty(value = "나이", required = true)
    private Integer age;

    @ApiModelProperty(value = "전화번호", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "유저 코드", required = true)
    private String userCode;

    @ApiModelProperty(value = "프로필 이미지", required = true)
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
