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
@Table(name = "transportation_mode")
public class TransportationModeEntity extends BaseEntity {

    @Column(name = "mode_name", nullable = false)
    private String modeName;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "icon_url", nullable = false, columnDefinition = "text")
    private String iconUrl;

    @OneToMany(mappedBy = "transportationModeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> tourPackageTransportationEntities = new ArrayList<>();

}