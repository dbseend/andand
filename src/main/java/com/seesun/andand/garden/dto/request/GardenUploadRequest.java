package com.seesun.andand.garden.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenUploadRequest {

    private String appUserId;

    private Long mateId;

    private MultipartFile picture;

    private String content;

}
