package com.seesun.andand.appUser.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.seesun.andand.appUserMate.domain.AppUserMate;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserGarden.domain.AppUserGarden;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String userCode;

    private Long point;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<AppUserMate> appUserMateList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUserDaily> dailyList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUserGarden> gardenList;

    @Builder
    public AppUser(String name, Integer age, String phoneNumber, String userCode, Long point) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.userCode = userCode;
        this.point = point;
    }

    public void updateInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

//    public void updateMate(Mate mate) {
//        this.mate = mate;
//    }
}
