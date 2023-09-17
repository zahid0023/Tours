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
@Table(name = "tour_room_category")
public class TourRoomCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "room_category_name", nullable = false)
    private String roomCategoryName;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "tourRoomCategoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = new ArrayList<>();

}