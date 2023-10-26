package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    private List<TransportationPackageEntity> transportationPackageEntities = new ArrayList<>();


    @Column(name = "package_price_per_person", precision = 10, scale = 2)
    private BigDecimal packagePricePerPerson;

    @Column(name = "total_package_price")
    private BigDecimal totalPackagePrice;

    @Column(name = "default_food_option_price")
    private BigDecimal defaultFoodOptionPrice;

    @Column(name = "default_accommodation_option_price")
    private BigDecimal defaultAccommodationOptionPrice;

    @Column(name = "default_transfer_option_price")
    private BigDecimal defaultTransferOptionPrice;

    @Column(name = "guide_price")
    private BigDecimal guidePrice;

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationOptionEntity> accommodationOptionEntities = new LinkedList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodOptionEntity> foodOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferOptionEntity> tourTransferOptionEntities = new ArrayList<>();

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