package com.seesun.andand.appUserDaily.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppUserDailyRepository extends JpaRepository<AppUserDaily, Long> {

//    Optional<AppUserDaily> findByAppUserIdAndCreateDateTimeBetween(Long appUserId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
