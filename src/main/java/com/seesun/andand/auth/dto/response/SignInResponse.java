package com.seesun.andand.auth.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResponse {

    private String userId;

    private String token;

    public SignInResponse(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
