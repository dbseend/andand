package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResponse {

    private String userId;

    private String name;

    private String profileImage;

    private String userCode;

    public SignUpResponse(AppUser appUser) {
        this.userId = appUser.getUserId();
        this.name = appUser.getName();
        this.profileImage = appUser.getProfileImage();
        this.userCode = appUser.getUserCode();
    }
}
