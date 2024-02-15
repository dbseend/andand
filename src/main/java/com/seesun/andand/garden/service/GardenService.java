package com.seesun.andand.garden.service;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GardenService {

    private final AppUserRepository appUserRepository;

    public void uploadGarden(Long appUserId, Long mateId, MultipartFile file) throws IOException {

        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        LocalDate today = LocalDate.now();

    }
}
