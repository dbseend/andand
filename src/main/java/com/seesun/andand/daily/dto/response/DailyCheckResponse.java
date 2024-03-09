package com.seesun.andand.daily.dto.response;

import com.seesun.andand.appUserDaily.dto.AppUserDailyResponse;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyCheckResponse {
    @Schema(description = "데일리 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "메이트 코드정보", required = true)
    private String code;

    @Schema(description = "데일리 정보", required = true)
    private String tag;

}

