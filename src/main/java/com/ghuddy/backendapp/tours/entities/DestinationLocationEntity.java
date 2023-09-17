package com.ghuddy.backendapp.tours.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "location")
public class DestinationLocationEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "destinationLocationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourLocationEntity> tourLocationEntities = new ArrayList<>();

}