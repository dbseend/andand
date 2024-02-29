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

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String userCode;

    private Long point;

    private String token;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    // 회원정보 수정 메소드
    public void updateInfo(String name, Integer age, String phoneNumber, String newProfileImage) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.profileImage = newProfileImage;
    }

    // 포인트 추가 메소드
    public void addPoint() {
        this.point += 50L;
    }

    // 토큰 업데이트 메소드
    public void updateToken(String token) {
        this.token = token;
    }
}
