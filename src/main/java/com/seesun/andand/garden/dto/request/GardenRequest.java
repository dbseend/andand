package com.seesun.andand.garden.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GardenRequest {

    private Long appUserId;

    private String picture;

    private String content;
}
