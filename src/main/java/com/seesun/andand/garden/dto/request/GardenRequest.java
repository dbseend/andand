package com.seesun.andand.garden.dto.request;

import com.seesun.andand.garden.domain.Garden;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GardenRequest {

    @ApiModelProperty(value = "회원 ID", required = true)
    private Long appUserId;

    @ApiModelProperty(value = "제목", required = true)
    private String picture;

    @ApiModelProperty(value = "내용", required = true)
    private String content;
}
