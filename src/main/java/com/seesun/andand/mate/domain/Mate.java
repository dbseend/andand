package com.seesun.andand.mate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.garden.domain.Garden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mate")
public class Mate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private Integer dailyContinuousDays;

    private Integer gardenLevel;

    private Integer gardenNum;

    @OneToMany(mappedBy = "mate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AppUserMate> appUserMateList;

    @OneToMany(mappedBy = "mate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Daily> dailyList;

    @OneToMany(mappedBy = "mate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Garden> gardenList;

    public Mate(String code) {
        this.code = code;
        this.dailyContinuousDays = 0;
        this.gardenLevel = 1;
        this.gardenNum = 0;
    }

    public void addDailyContinuousDays() {
        this.dailyContinuousDays++;
    }

    public void initDailyContinuousDays() {
        this.dailyContinuousDays = 0;
    }

    public void addGardenNum() {
        this.gardenNum++;
    }

    //gardennum이 일정 숫자를 초과하면 레벨 증가 함수
    public void addGardenLevel() {
        int gardenLevel = 1;

        if (gardenNum >= 30) {
            gardenLevel++;
        } else if (gardenNum >= 65) {
            gardenLevel++;
        } else if (gardenNum >= 90) {
            gardenLevel++;
        }
    }
}
