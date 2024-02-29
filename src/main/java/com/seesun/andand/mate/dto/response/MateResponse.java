package com.seesun.andand.mate.dto.response;

import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateResponse {

    private Integer gardenNum;

    private Integer gardenLevel;

    private Integer dailyContinuousDays;

    public MateResponse(Mate mate){
        this.gardenNum = mate.getGardenNum();
        this.gardenLevel = mate.getGardenLevel();
        this.dailyContinuousDays = mate.getDailyContinuousDays();
    }
}
