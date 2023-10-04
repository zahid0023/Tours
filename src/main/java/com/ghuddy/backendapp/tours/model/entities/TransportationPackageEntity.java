package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.enums.TripType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tour_package_transportation_package")
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

    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "net_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal netPrice;

    @NotNull
    @Column(name = "added_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal addedPrice;

    @NotNull
    @Column(name = "total_transportation_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalTransportationPackagePrice;

    @NotNull
    @Column(name = "is_included", nullable = false)
    private Boolean isIncluded = false;

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