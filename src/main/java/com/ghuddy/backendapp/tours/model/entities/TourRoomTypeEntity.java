package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_room_type")
public class TourRoomTypeEntity extends BaseEntity {

    @Column(name = "room_type_name", nullable = false)
    private String roomTypeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "tourRoomTypeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = new ArrayList<>();

}