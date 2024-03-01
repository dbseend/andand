package com.seesun.andand.garden.dto.response;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.mate.domain.Mate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenResponse {

    @Schema(description = "정원 게시물 ID", example = "1", required = true)
    private Long id;

    @Schema(description = "정원 게시물 작성자 정보", required = true)
    private AppUser appUser;

    @Schema(description = "정원 게시물 메이트 정보", required = true)
    private Mate mate;

    @Schema(description = "정원 게시물 사진", required = true)
    private String picture;

    @Schema(description = "정원 게시물 내용", example = "안녕하세요", required = true)
    private String content;

    @Schema(description = "정원 게시물 작성 일시", required = true)
    private LocalDateTime createdAt;
}
