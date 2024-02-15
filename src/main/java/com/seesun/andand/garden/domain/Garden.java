package com.seesun.andand.garden.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.AppUserGarden.domain.AppUserGarden;
import com.seesun.andand.group.domain.Mate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "garden")
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<AppUserGarden> appUserGardenList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mate_id")
    @JsonManagedReference
    private Mate mate;

    @Builder
    public Garden(Long id, Mate mate) {
        this.id = id;
        this.mate = mate;
    }
}
