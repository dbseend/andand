package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.dto.response.MateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private Long id;

    private String userId;

    private String profileImage;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String userCode;

    private Long point;

    private List<MateResponse> mateList;

    public AppUserResponse(AppUser appUser) {
        this.id = appUser.getId();
        this.userId = appUser.getUserId();
        this.profileImage = appUser.getProfileImage();
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
        this.userCode = appUser.getUserCode();
        this.point = appUser.getPoint();
    }

    public AppUserResponse(AppUser appUser, List<MateResponse> mateList) {
        this.id = appUser.getId();
        this.userId = appUser.getUserId();
        this.profileImage = appUser.getProfileImage();
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
        this.userCode = appUser.getUserCode();
        this.point = appUser.getPoint();
        this.mateList = mateList;
    }
}
