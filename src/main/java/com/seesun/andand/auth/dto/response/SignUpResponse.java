package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    private Long id;

    private String userId;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String profileImage;

    private String userCode;

    private Long point;

    public SignUpResponse(AppUser appUser) {
        this.id = appUser.getId();
        this.userId = appUser.getUserId();
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
        this.profileImage = appUser.getProfileImage();
        this.userCode = appUser.getUserCode();
        this.point = appUser.getPoint();
    }
}
