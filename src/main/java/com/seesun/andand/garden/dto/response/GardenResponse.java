package com.seesun.andand.garden.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    @ApiModelProperty(value = "정원 게시물 ID", required = true)
    private Long id;

    @ApiModelProperty(value = "정원 게시물 작성자", required = true)
    private AppUser appUser;

    @ApiModelProperty(value = "정원 게시물 메이트 정보", required = true)
    private Mate mate;

    @ApiModelProperty(value = "정원 게시물 제목", required = true)
    private String picture;

    @ApiModelProperty(value = "정원 게시물 내용", required = true)
    private String content;

    @ApiModelProperty(value = "정원 게시물 생성 일시", required = true)
    private LocalDateTime createdAt;
}
