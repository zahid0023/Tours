package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "transportation_packages")
public class TransportationPackageEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_route_id", nullable = false)
    private TransportationRouteEntity transportationRouteEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_mode_id", nullable = false)
    private TransportationModeEntity transportationModeEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_brand_id", nullable = false)
    private TransportationBrandEntity transportationBrandEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transportation_provider_id", nullable = false)
    private TransportationProviderEntity transportationProviderEntity;


    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @Column(name = "is_ac", nullable = false)
    private Boolean isAc;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackageEntity tourPackageEntity;
    
    @NotNull
    @Column(name = "per_person_transportation_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perPersonTransportationPackagePrice;

    @OneToMany(mappedBy = "transportationPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTransportationPackageEntity> availabilityGeneratedTransportationPackageEntities = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransportationPackageEntity that = (TransportationPackageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}