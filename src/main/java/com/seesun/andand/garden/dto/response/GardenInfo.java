package com.seesun.andand.garden.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenInfo {

    private Integer gardenLevel;

    private Integer gardenNum;

    private List<String> appUserNameList;

}
