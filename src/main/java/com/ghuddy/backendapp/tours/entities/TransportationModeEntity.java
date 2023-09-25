package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
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
@Table(name = "transportation_mode")
public class TransportationModeEntity extends BaseEntity {

    @Size(max = 255)
    @NotNull
    @Column(name = "mode_name", nullable = false)
    private String modeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @NotNull
    @Column(name = "icon_url", nullable = false, columnDefinition = "text")
    private String iconUrl;

    @OneToMany(mappedBy = "transportationModeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageTransportationEntity> tourPackageTransportationEntities = new ArrayList<>();

}