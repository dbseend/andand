package com.seesun.andand.daily.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUserDaily.domain.AppUserDaily;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.mate.domain.Mate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "daily")
public class Daily extends BaseEntity {

    Boolean isBothUploaded;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tag;
    @OneToMany(mappedBy = "daily", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<AppUserDaily> appUserDailyList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    @JsonManagedReference
    private Mate mate;

    @Builder
    public Daily(String tag, Mate mate, Boolean isBothUploaded) {
        this.tag = tag;
        this.mate = mate;
        this.isBothUploaded = false;
        this.appUserDailyList = new ArrayList<>();
    }

    public void updateIsBothUploaded() {
        this.isBothUploaded = true;
    }
}
