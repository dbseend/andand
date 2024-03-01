package com.seesun.andand.appUser.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "회원 ID", required = true)
    private Long appUserId;

    @ApiModelProperty(value = "프로필 이미지", required = true)
    private MultipartFile profileImage;

    @ApiModelProperty(value = "이름", required = true)
    private String name;

    @ApiModelProperty(value = "나이", required = true)
    private Integer age;

    @ApiModelProperty(value = "전화번호", required = true)
    private String phoneNumber;
}
