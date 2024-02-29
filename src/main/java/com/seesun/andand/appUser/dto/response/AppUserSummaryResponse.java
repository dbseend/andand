package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserSummaryResponse {

    private String userName;

    private String profileImage;

    public AppUserSummaryResponse(AppUser appUser) {
        this.userName = appUser.getName();
        this.profileImage = appUser.getProfileImage();
    }
}
