package com.seesun.andand.garden.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenInfo {

    @ApiModelProperty(value = "정원 레벨", required = true)
    private Integer gardenLevel;

    @ApiModelProperty(value = "정원 게시물 개수", required = true)
    private Integer gardenNum;

    @ApiModelProperty(value = "메이트 사용자 이름 리스트", required = true)
    private List<String> appUserNameList;

}
