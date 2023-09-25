package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.DestinationLocationEntity;
import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_route")
public class TransportationRouteEntity extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_location_id", nullable = false)
    private DestinationLocationEntity sourceLocation;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_location_id", nullable = false)
    private DestinationLocationEntity destinationLocation;

    @OneToMany(mappedBy = "transportationRouteEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageTransportationEntity> tourPackageTransportationEntities = new ArrayList<>();

}