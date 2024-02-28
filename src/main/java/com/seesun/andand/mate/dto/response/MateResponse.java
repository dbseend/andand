package com.seesun.andand.mate.dto.response;

import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MateResponse {

    private Long id;

    private String userCode;

    private List<AppUserResponse> appUserList;

    public MateResponse(Mate mate) {
        this.id = mate.getId();
        this.userCode = mate.getCode();
        this.appUserList = mate.getAppUserMateList().stream()
                .map(appUserMate -> new AppUserResponse(appUserMate.getAppUser()))
                .toList();
    }
}
