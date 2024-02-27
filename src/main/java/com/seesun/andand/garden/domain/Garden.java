package com.seesun.andand.garden.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.mate.domain.Mate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "garden")
public class Garden extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String content;

    @ManyToOne()
    @JoinColumn(name = "app_user_id")
    @JsonManagedReference
    private AppUser appUser;

    @ManyToOne()
    @JoinColumn(name = "mate_id")
    @JsonManagedReference
    private Mate mate;

    @Builder
    public Garden(Long id, Mate mate) {
        this.id = id;
        this.mate = mate;
    }
}
