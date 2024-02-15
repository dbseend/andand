package com.seesun.andand.daily.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class DailyUploadRequest {

    private Long appUserId;

    private Long mateId;

    private MultipartFile file;

}
