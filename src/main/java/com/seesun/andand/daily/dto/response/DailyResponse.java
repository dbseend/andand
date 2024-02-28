package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponse {

    private Long id;

    private Mate mate;

    private String tag;

    private Boolean isBothUploaded;

    private List<AppUserDailyResponse> appUserDailyList;
}
