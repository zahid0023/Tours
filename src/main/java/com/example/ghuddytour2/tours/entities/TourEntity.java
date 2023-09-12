package com.example.ghuddytour2.tours.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "thumb_image_url", columnDefinition = "text")
    private String thumbImageUrl;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_location_id")
    private TourLocationEntity tourLocation;

    @OneToMany(mappedBy = "tourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourItineraryEntity> tourItineraryEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourSpecialityEntity> tourSpecialityEntities = new ArrayList<>();

}