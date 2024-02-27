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

    private String profileImage;

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
    public AppUser(String userId, String password, String name, String profileImage, Integer age, String phoneNumber, String userCode, Long point) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.userCode = userCode;
        this.point = point;
    }

    public void updateInfo(AppUser appUser) {
        this.name = appUser.getName();
        this.age = appUser.getAge();
        this.phoneNumber = appUser.getPhoneNumber();
        this.profileImage = appUser.getProfileImage();
    }

    public void addPoint() {
        this.point += 50L;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public void uploadProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
