package com.seesun.andand.auth.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private String userId;

    private String password;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String picture;

    public AppUser toEntity(String userCode) {
        return AppUser.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .userCode(userCode)
                .picture(picture)
                .point(0L)
                .build();
    }
}
