package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.mate.dto.response.MateResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    @ApiModelProperty(value = "회원 정보", required = true)
    private AppUserSummaryResponse appUserSummaryResponse;

    @ApiModelProperty(value = "파트너 정보", required = true)
    private PartnerResponse partnerResponse;

    @ApiModelProperty(value = "메이트 정보", required = true)
    private MateResponse mateResponse;

    @ApiModelProperty(value = "토큰", required = true)
    private String token;
}
