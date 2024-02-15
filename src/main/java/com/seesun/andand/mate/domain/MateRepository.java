package com.seesun.andand.mate.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MateRepository extends JpaRepository<Mate, Long> {

    Optional<Mate> findByCode(String code);
}
