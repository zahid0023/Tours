package com.ghuddy.backendapp.tours.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_accommodation_type")
public class TourAccommodationTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "accommodation_type_name", nullable = false)
    private String accommodationTypeName;

    @OneToMany(mappedBy = "tourAccommodationTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourAccommodationEntity> tourAccommodationEntities = new ArrayList<>();

}