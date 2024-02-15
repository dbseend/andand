package com.seesun.andand.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.daily.domain.DailyRepository;
import com.seesun.andand.daily.dto.response.InfoForDaily;
import com.seesun.andand.daily.service.DailyService;
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
    private final DailyRepository dailyRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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

    public String generateRandomCode(int length) {

        final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final SecureRandom random = new SecureRandom();

        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

    public boolean isBothUploaded(Long mateId) {

        boolean isBothUploaded = false;

        InfoForDaily infoForDaily = getInfoForDaily(mateId);
        Daily daily = infoForDaily.getDaily();
        if (daily.getAppUserDailyList().size() == 2) {
            isBothUploaded = true;
        }

        return isBothUploaded;
    }

    public InfoForDaily getInfoForDaily(Long mateId) {

        Mate mate = mateRepository.findById(mateId).
                orElseThrow(() -> new IllegalArgumentException("해당 메이트가 없습니다."));

        LocalDate targetCreateDate = LocalDate.now(); // 오늘의 날짜와 시간을 얻습니다.
        LocalDateTime startDateTime = targetCreateDate.atStartOfDay();
        LocalDateTime endDateTime = targetCreateDate.atTime(23, 59, 59);
        Daily daily = dailyRepository.findByMateAndCreateDateBetween(mate, startDateTime, endDateTime).
                orElseThrow(() -> new IllegalArgumentException("해당 일일이 없습니다."));

        return new InfoForDaily(mate, daily, startDateTime, endDateTime);
    }
}
