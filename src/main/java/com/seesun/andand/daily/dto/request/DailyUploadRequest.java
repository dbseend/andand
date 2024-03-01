package com.seesun.andand.daily.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class DailyUploadRequest {

    @ApiModelProperty(value = "회원 ID", required = true)
    private Long appUserId;

    @ApiModelProperty(value = "메이트 ID", required = true)
    private Long mateId;

    @ApiModelProperty(value = "내용", required = true)
    private MultipartFile file;
}
