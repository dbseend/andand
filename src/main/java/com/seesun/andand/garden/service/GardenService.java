package com.seesun.andand.garden.service;

import com.seesun.andand.garden.domain.GardenRepository;
import com.seesun.andand.mate.domain.MateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GardenService {
    private final GardenRepository gardenRepository;
    private final MateRepository mateRepository;

    //레벨표시
    public int getlevel(Long mateId) {
        return mateRepository.findById(mateId).get().getLevel();
    }


}
