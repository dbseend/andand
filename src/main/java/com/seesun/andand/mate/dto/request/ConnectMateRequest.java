package com.seesun.andand.mate.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectMateRequest {

    @Schema(description = "사용자 ID", example = "1", required = true)
    private String userId;

    @Schema(description = "동행 코드", example = "1", required = true)
    private String userCode;
}
