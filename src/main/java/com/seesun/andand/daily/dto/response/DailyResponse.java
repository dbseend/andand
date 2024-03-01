package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyResponse {

    @ApiModelProperty(value = "일일 ID", required = true)
    private Long id;

    @ApiModelProperty(value = "일일 정보", required = true)
    private Mate mate;

    @ApiModelProperty(value = "일일 정보", required = true)
    private String tag;

    @ApiModelProperty(value = "일일 정보", required = true)
    private Boolean isBothUploaded;

    @ApiModelProperty(value = "일일 정보", required = true)
    private List<AppUserDailyResponse> appUserDailyList;

}
