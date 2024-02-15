package com.seesun.andand.appUser.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private Long id;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String userCode;
}
