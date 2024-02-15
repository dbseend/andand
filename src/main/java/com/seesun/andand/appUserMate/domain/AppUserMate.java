package com.seesun.andand.appUserMate.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.mate.domain.Mate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "app_user_mate")
public class AppUserMate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    private Mate mate;

    @Builder
    public AppUserMate(AppUser appUser, Mate mate) {
        this.appUser = appUser;
        this.mate = mate;
    }
}
