package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private String userId;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String userCode;

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
