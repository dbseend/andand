package com.seesun.andand.util;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class S3Controller {

    private final UtilService utilService;

    @PostMapping("/upload")
    @ApiOperation(value = "파일 업로드", notes = "파일 업로드 API")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("directory") String directoryPath) throws IOException {

        String url = utilService.uploadImage(multipartFile, directoryPath);

        return ResponseEntity.ok(url);
    }
}
