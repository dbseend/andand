package com.seesun.andand.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.dto.response.DailyInfo;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilService {

    private final AmazonS3 amazonS3;
    private final MateRepository mateRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // 이미지 업로드
    public String uploadImage(MultipartFile multipartFile, String directoryPath) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("originalFilename: " + originalFilename);

        String fullFilePath = directoryPath + "/" + originalFilename;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        amazonS3.putObject(bucket, fullFilePath, multipartFile.getInputStream(), metadata);
        log.info(amazonS3.getUrl(bucket, fullFilePath).toString());
        return amazonS3.getUrl(bucket, fullFilePath).toString();
    }

    // 랜덤 코드 생성
    public String generateRandomCode() {

        final int length = 6;

        final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final SecureRandom random = new SecureRandom();

        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

    // 두 명이 모두 업로드 했는지 확인
    public boolean isBothUploaded(Long mateId) {

        boolean isBothUploaded = false;

        LocalDate today = LocalDate.now(); // 오늘의 날짜와 시간을 얻습니다.
        DailyInfo dailyInfo = getInfoForDaily(mateId, today);
        Daily daily = dailyInfo.getDaily();

        if (daily.getAppUserDailyList().size() == 2) {
            isBothUploaded = true;
        }

        return isBothUploaded;
    }

    // 해당 날짜의 DailyInfo를 얻습니다.
    public DailyInfo getInfoForDaily(Long mateId, LocalDate targetCreateDate) {

        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        LocalDateTime startDateTime = targetCreateDate.atStartOfDay();
        LocalDateTime endDateTime = targetCreateDate.atTime(23, 59, 59);
        Daily daily = new Daily("웃음", mate, false);

        return new DailyInfo(mate, daily, startDateTime, endDateTime);
    }

    public boolean isSameDay(LocalDateTime createDate) {
        return LocalDate.now().isEqual(createDate.toLocalDate());
    }
}
