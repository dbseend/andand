package com.seesun.andand.appUser.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdateRequest {

    private Long appUserId;

    private Long id;

    private String userId;

    private String profileImage;

    private String name;

    private Integer age;

    private String phoneNumber;

    public AppUserUpdateRequest(AppUser appUser) {
        this.appUserId = appUser.getId();
        this.id = appUser.getId();
        this.userId = appUser.getUserId();
        this.profileImage = appUser.getProfileImage();
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
    }

}
