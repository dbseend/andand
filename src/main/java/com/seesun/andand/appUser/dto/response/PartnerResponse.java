package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    @ApiModelProperty(value = "이름", required = true)
    private String partnerUserName;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private String partnerProfileImage;

    public PartnerResponse(AppUser appUser) {
        this.partnerUserName = appUser.getName();
        this.partnerProfileImage = appUser.getProfileImage();
    }
}
