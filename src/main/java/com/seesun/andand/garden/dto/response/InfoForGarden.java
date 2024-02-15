package com.seesun.andand.garden.dto.response;

import com.seesun.andand.garden.domain.Garden;
import com.seesun.andand.mate.domain.Mate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoForGarden {

    private Mate mate;

    private Garden garden;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
