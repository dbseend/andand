package com.seesun.andand.appUserGarden.dto;

import com.seesun.andand.appUserGarden.domain.AppUserGarden;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserGardenResponse {

    private Long id;

    private String picture;

    private String content;

    public AppUserGardenResponse(Long id, String picture, String content) {
        this.id = id;
        this.picture = picture;
        this.content = content;
    }

    public AppUserGardenResponse(AppUserGarden appUserGarden) {
        this.id = appUserGarden.getId();
        this.picture = appUserGarden.getPicture();
        this.content = appUserGarden.getContent();
    }
}
