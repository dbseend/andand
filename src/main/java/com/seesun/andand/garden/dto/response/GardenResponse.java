package com.seesun.andand.garden.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    private Long id;

    private AppUser appUser;

    private Mate mate;

    private String picture;

    private String content;

    private LocalDateTime createdAt;
}
