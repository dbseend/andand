package com.seesun.andand.mate.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectMateRequest {

    @ApiModelProperty(value = "사용자 ID", required = true)
    private String userId;

    @ApiModelProperty(value = "사용자 코드", required = true)
    private String userCode;
}
