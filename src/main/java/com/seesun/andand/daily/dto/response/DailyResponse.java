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
public class DailyResponse {

    @Schema(description = "데일리 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "데일리 정보", required = true)
    private String tag;

    @Schema(description = "시작 일시", required = true)
    private Boolean isBothUploaded;

    @Schema(description = "업로드한 게시물", required = true)
    private List<AppUserDailyResponse> appUserDailyList;

}
