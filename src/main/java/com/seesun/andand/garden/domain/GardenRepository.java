package com.seesun.andand.garden.domain;

import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.mate.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GardenRepository extends JpaRepository<Garden, Long> {

    Optional<Garden> findByMateAndCreateDateBetween(Mate mate, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
