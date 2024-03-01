package com.seesun.andand.daily.dto.response;

import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyInfo {

    @Schema(description = "메이트 정보", required = true)
    private Mate mate;

    @Schema(description = "데일리 정보", required = true)
    private Daily daily;

    @Schema(description = "시작 일시", required = true)
    private LocalDateTime startDateTime;

    @Schema(description = "종료 일시", required = true)
    private LocalDateTime endDateTime;
}
