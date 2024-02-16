package com.seesun.andand.appUserGarden.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.garden.domain.Garden;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user_garden")
public class AppUserGarden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String picture;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    @JsonManagedReference
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_id")
    @JsonManagedReference
    private Garden garden;

    @Builder
    public AppUserGarden(String picture, String content, AppUser appUser, Garden garden) {
        this.picture = picture;
        this.content = content;
        this.appUser = appUser;
        this.garden = garden;
    }
}
