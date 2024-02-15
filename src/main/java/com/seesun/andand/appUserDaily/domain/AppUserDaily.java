package com.seesun.andand.appUserDaily.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.daily.domain.Daily;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user_daily")
public class AppUserDaily extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonManagedReference
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_id")
    @JsonManagedReference
    private Daily daily;

    @Builder
    public AppUserDaily(AppUser appUser, Daily daily, String picture) {
        this.appUser = appUser;
        this.daily = daily;
        this.picture = picture;
    }

    public void updatePicture(String picture) {
        this.picture = picture;
    }
}
