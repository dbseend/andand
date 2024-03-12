package com.seesun.andand.garden.domain;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.mate.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;
import java.util.Optional;

public interface GardenRepository extends JpaRepository<Garden, Long> {


    Optional<Garden> findById(Long gardenId);

    List<Garden> findByAppUser(AppUser appUser);


    Optional<Garden> findByAppUserAndCreateDateBetween(
            AppUser appUser,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    Optional<Garden> findTopByCreateDateBetweenOrderByCreateDateDesc(LocalDateTime todayStart, LocalDateTime todayEnd);

    Optional<Garden> findByMateAndCreateDateBetween(Mate mate, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
