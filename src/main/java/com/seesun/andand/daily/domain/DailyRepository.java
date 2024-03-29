package com.seesun.andand.daily.domain;

import com.seesun.andand.mate.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DailyRepository extends JpaRepository<Daily, Long> {

    List<Daily> findByCreateDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
