package com.seesun.andand.appUser.dto.request;

import com.seesun.andand.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserUpdateRequest {

    private Long appUserId;

    private MultipartFile profileImage;

    private String name;

    private Integer age;

    private String phoneNumber;
}
