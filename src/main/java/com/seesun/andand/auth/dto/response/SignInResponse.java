package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.mate.dto.response.MateResponse;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    @Schema(description = "회원 ID", example = "1", required = true)
    private AppUserSummaryResponse appUserSummaryResponse;

    @Schema(description = "파트너 정보", required = true)
    private PartnerResponse partnerResponse;

    @Schema(description = "메이트 정보", required = true)
    private MateResponse mateResponse;

    @Schema(description = "토큰", required = true)
    private String token;

    @Schema(description = "오늘의 태그", required = true)
    private String todayTag;
}
