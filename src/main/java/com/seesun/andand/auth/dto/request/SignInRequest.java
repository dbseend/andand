package com.seesun.andand.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @Schema(description = "회원 ID", example = "1", required = true)
    private String userId;

    @Schema(description = "비밀번호", example = "1234", required = true)
    private String password;
}
