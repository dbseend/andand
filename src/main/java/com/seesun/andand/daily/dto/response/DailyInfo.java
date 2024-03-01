package com.seesun.andand.daily.dto.response;

import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyInfo {

    @ApiModelProperty(value = "메이트 정보", required = true)
    private Mate mate;

    @ApiModelProperty(value = "일일 정보", required = true)
    private Daily daily;

    @ApiModelProperty(value = "시작 일시", required = true)
    private LocalDateTime startDateTime;

    @ApiModelProperty(value = "종료 일시", required = true)
    private LocalDateTime endDateTime;
}
