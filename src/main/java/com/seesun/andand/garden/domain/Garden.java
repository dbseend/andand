package com.seesun.andand.garden.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.andand.appUser.domain.AppUser;
import com.seesun.andand.appUser.dto.response.AppUserResponse;
import com.seesun.andand.configuration.BaseEntity;
import com.seesun.andand.mate.domain.Mate;
import com.seesun.andand.mate.dto.response.MateResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public Garden(String image, String content, AppUser appUser, Mate mate) {
        this.image = image;
        this.content = content;
        this.appUser = appUser;
        this.mate = mate;
    }

    public void update(String content, MultipartFile image) throws IOException {
        this.content = content;
        this.image = image.getOriginalFilename();
    }

}
