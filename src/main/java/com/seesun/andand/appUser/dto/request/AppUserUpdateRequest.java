package com.seesun.andand.appUser.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdateRequest {

    @Schema(description = "회원 ID", example = "1", required = true)
    private Long appUserId;

    @Schema(description = "프로필 이미지", required = true)
    private MultipartFile profileImage;

    @Schema(description = "이름", example = "홍길동", required = true)
    private String name;

    @Schema(description = "나이", example = "20", required = true)
    private Integer age;

    @Schema(description = "성별", example = "M", required = true)
    private String phoneNumber;
}
