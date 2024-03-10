package com.seesun.andand.garden.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenAllResponse {

    private List<GardenResponse> gardenList;

    private Integer count;
}
