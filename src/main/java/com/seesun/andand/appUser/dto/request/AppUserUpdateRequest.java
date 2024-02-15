package com.seesun.andand.appUser.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdateRequest {

    private String name;

    private String phoneNumber;

}
