package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tour_package")
public class TourPackageEntity extends BaseEntity {

    @Column(name = "tour_package_name", nullable = false)
    private String tourPackageName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscribed_tour_id")
    private SubscribedTourEntity subscribedTourEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_type_id", nullable = false)
    private TourPackageTypeEntity tourPackageType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationPackageEntity> accommodationPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> transportationPackageEntities = new ArrayList<>();

    @NotNull
    @Column(name = "is_food_included", nullable = false)
    private Boolean isFoodIncluded;

    @NotNull
    @Column(name = "is_accommodation_included", nullable = false)
    private Boolean isAccommodationIncluded;

    @NotNull
    @Column(name = "is_transportation_included", nullable = false)
    private Boolean isTransportationIncluded;

    @NotNull
    @Column(name = "is_transfer_included", nullable = false)
    private Boolean isTransferIncluded;

    @Column(name = "package_price_per_person", precision = 10, scale = 2)
    private BigDecimal packagePricePerPerson;

    @Column(name = "total_package_price")
    private BigDecimal totalPackagePrice;

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferPackageEntity> transferPackageEntities = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourPackageEntity that = (TourPackageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}