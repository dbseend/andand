package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserSummaryResponse;
import com.seesun.andand.appUser.dto.response.PartnerResponse;
import com.seesun.andand.mate.dto.response.MateResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponse {

    private AppUserSummaryResponse appUserSummaryResponse;

    private PartnerResponse partnerResponse;

    private MateResponse mateResponse;

    private String token;
}
