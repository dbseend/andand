package com.seesun.andand.daily.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class DailyUploadRequest {

    @Schema(description = "회원 ID", example = "1", required = true)
    private Long appUserId;

    @Schema (description = "동행 ID", example = "1", required = true)
    private Long mateId;

    @Schema(description = "내용", example = "안녕하세요", required = true)
    private MultipartFile file;
}
