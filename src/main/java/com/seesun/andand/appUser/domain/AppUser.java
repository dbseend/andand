package com.seesun.andand.appUser.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.garden.domain.Garden;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String picture;

    private String name;

    private Integer age;

    private String phoneNumber;

    @Column(unique = true)
    private String userCode;

    private Long point;

    private String token;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private List<AppUserMate> appUserMateList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AppUserDaily> dailyList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Garden> gardenList;

    @Builder
    public AppUser(String userId, String password, String name, Integer age, String phoneNumber, String userCode, String picture, Long point) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.userCode = userCode;
        this.picture = picture;
        this.point = point;
    }

    public void updateInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addPoint() {
        this.point += 50L;
    }

    public void updateToken(String token) {
        this.token = token;
    }
}
