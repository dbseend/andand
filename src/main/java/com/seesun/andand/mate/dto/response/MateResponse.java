package com.seesun.andand.mate.dto.response;

import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateResponse {

    @ApiModelProperty(value = "정원 게시물 수", required = true)
    private Integer gardenNum;

    @ApiModelProperty(value = "정원 레벨", required = true)
    private Integer gardenLevel;

    @ApiModelProperty(value = "연속 일수", required = true)
    private Integer dailyContinuousDays;

    public MateResponse(Mate mate){
        this.gardenNum = mate.getGardenNum();
        this.gardenLevel = mate.getGardenLevel();
        this.dailyContinuousDays = mate.getDailyContinuousDays();
    }
}
