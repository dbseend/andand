package com.seesun.andand.garden.dto.response;

import com.seesun.andand.appUserGarden.domain.AppUserGarden;
import com.seesun.andand.appUserGarden.dto.AppUserGardenResponse;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    private Long id;

    private Mate mate;

    private List<AppUserGardenResponse> appUserGardenList;
}
