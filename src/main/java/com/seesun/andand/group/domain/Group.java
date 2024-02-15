package com.seesun.andand.group.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.daily.domain.Daily;
import com.seesun.andand.garden.domain.Garden;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "group")
public class Group extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    private Integer dailyContinuousDays;

    private Integer gardenLevel;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUser> appUserList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Daily> dailyList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Garden> gardenList;

    @Builder
    public Group(Long id, String code, Integer dailyContinuousDays, Integer gardenLevel) {
        this.id = id;
        this.code = code;
        this.dailyContinuousDays = dailyContinuousDays;
        this.gardenLevel = gardenLevel;
    }
}
