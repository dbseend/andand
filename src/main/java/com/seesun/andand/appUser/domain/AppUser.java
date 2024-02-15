package com.seesun.andand.appUser.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.appUserGarden.domain.AppUserGarden;
import com.seesun.andand.group.domain.Mate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    @JsonManagedReference
    private Mate mate;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUserDaily> dailyList;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUserGarden> gardenList;

    @Builder
    public AppUser(Long id, String name, Integer age, String phoneNumber, String userCode, Long point, Mate mate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.userCode = userCode;
        this.point = point;
        this.mate = mate;
    }

    public void update(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
