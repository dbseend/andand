package com.seesun.andand.appUser.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    @Schema(description = "이름", example = "홍길동", required = true)
    private String partnerUserName;

    @Schema(description = "프로필 이미지", required = true)
    private String partnerProfileImage;

    public PartnerResponse(AppUser appUser) {
        this.partnerUserName = appUser.getName();
        this.partnerProfileImage = appUser.getProfileImage();
    }
}
