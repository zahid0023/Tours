package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
    private List<AccommodationOptionEntity> accommodationOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodOptionEntity> foodOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferOptionEntity> transferOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> transportationPackageEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuideOptionEntity> guideOptionEntityList = new LinkedList<>();
    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpotEntryEntity> spotEntryEntities = new ArrayList<>();

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