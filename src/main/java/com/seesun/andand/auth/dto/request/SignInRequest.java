package com.seesun.andand.auth.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @ApiModelProperty(value = "회원 ID", required = true)
    private String userId;

    @ApiModelProperty(value = "비밀번호", required = true)
    private String password;
}
