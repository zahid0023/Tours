package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.enums.TripType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tour_package_transportation")
public class TourPackageTransportationEntity extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_route_id", nullable = false)
    private TransportationRouteEntity transportationRouteEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_mode_id", nullable = false)
    private TransportationModeEntity transportationModeEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_brand_id", nullable = false)
    private TransportationBrandEntity transportationBrand;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_provider_id", nullable = false)
    private TransportationProviderEntity transportationProviderEntity;


    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @NotNull
    @Column(name = "is_ac", nullable = false)
    private Boolean isAc;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackageEntity tourPackageEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourPackageTransportationEntity that = (TourPackageTransportationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}