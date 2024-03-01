package com.seesun.andand.mate.dto.response;

import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateResponse {

    @Schema(description = "정원 번호", example = "1", required = true)
    private Integer gardenNum;

    @Schema(description = "정원 레벨", example = "1", required = true)
    private Integer gardenLevel;

    @Schema(description = "연속 출석 일수", example = "1", required = true)
    private Integer dailyContinuousDays;

    public MateResponse(Mate mate){
        this.gardenNum = mate.getGardenNum();
        this.gardenLevel = mate.getGardenLevel();
        this.dailyContinuousDays = mate.getDailyContinuousDays();
    }
}
