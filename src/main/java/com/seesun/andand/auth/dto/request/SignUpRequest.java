package com.seesun.andand.auth.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    private String userId;

    private String password;

    private String name;

    private Integer age;

    private String phoneNumber;

    private MultipartFile profileImage;

    public AppUser toEntity(String userCode, String profileImageName) {
        return AppUser.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .profileImage(profileImageName)
                .userCode(userCode)
                .point(0L)
                .build();
    }
}
