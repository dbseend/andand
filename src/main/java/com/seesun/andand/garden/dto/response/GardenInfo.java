package com.seesun.andand.garden.dto.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenInfo {

    @Schema(description = "정원 ID", example = "1", required = true)
    private Integer gardenLevel;

    @Schema(description = "정원 ID", example = "1", required = true)
    private Integer gardenNum;

    @Schema(description = "정원 ID", example = "1", required = true)
    private List<String> appUserNameList;

}
