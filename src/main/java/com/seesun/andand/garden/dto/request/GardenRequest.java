package com.seesun.andand.garden.dto.request;

import com.seesun.andand.garden.domain.Garden;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GardenRequest {

    @Schema (description = "사용자 ID", example = "1", required = true)
    private Long appUserId;

    @Schema(description = "사진", required = true)
    private String picture;

    @Schema(description = "내용", example = "안녕하세요", required = true)
    private String content;
}
