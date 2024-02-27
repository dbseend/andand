package com.seesun.andand.appUser.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUserId(String userId);
    Optional<AppUser> findByUserId(String userId);
}
