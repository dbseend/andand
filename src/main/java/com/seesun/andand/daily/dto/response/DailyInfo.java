package com.seesun.andand.daily.dto.response;

import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyInfo {

    private Mate mate;

    private Daily daily;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
