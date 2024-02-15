package com.seesun.andand.appUserMate.domain;

import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.mate.domain.Mate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserMateRepository extends JpaRepository<AppUserMate, Long> {

    Optional<AppUserMate> findByAppUserAndMate(AppUser appUser, Mate mate);
}
