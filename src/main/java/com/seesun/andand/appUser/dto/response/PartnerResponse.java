package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    private String partnerUserName;

    private String partnerProfileImage;

    public PartnerResponse(AppUser appUser) {
        this.partnerUserName = appUser.getName();
        this.partnerProfileImage = appUser.getProfileImage();
    }
}
