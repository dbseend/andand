package com.seesun.andand.auth.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @Schema(description = "회원 ID", example = "1", required = true)
    private String userId;

    @Schema(description = "비밀번호", example = "1234", required = true)
    private String password;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @Schema(description = "나이", example = "20", required = true)
    private Integer age;

    @Schema(description = "성별", example = "M", required = true)
    private String phoneNumber;

    @Schema(description = "프로필 이미지", required = true)
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
