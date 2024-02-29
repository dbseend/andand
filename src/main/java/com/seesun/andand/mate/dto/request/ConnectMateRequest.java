package com.seesun.andand.mate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConnectMateRequest {

    private String userId;

    private String userCode;
}
