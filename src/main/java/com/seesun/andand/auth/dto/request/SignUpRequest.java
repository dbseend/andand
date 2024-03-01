package com.seesun.andand.auth.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @ApiModelProperty(value = "회원 ID", required = true)
    private String userId;

    @ApiModelProperty(value = "비밀번호", required = true)
    private String password;

    @ApiModelProperty(value = "이름", required = true)
    private String name;

    @ApiModelProperty(value = "나이", required = true)
    private Integer age;

    @ApiModelProperty(value = "전화번호", required = true)
    private String phoneNumber;

    @ApiModelProperty(value = "프로필 이미지", required = true)
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
