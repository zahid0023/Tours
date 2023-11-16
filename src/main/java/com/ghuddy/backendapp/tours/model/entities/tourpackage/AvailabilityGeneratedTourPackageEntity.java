package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageAllOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageInclusiveOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_tour_packages")
public class AvailabilityGeneratedTourPackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;


    @NotNull
    @Column(name = "tour_start_date", nullable = false)
    private LocalDate tourStartDate;

    @NotNull
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @NotNull
    @Column(name = "bookable_seats", nullable = false)
    private Integer bookableSeats;

    @NotNull
    @Column(name = "is_accommodation_inclusive", nullable = false)
    private Boolean isAccommodationInclusive = false;

    @NotNull
    @Column(name = "is_food_inclusive", nullable = false)
    private Boolean isFoodInclusive = false;

    @NotNull
    @Column(name = "is_transfer_inclusive", nullable = false)
    private Boolean isTransferInclusive = false;

    @NotNull
    @Column(name = "is_guide_inclusive", nullable = false)
    private Boolean isGuideInclusive = false;

    @NotNull
    @Column(name = "is_spot_entry_inclusive", nullable = false)
    private Boolean isSpotEntryInclusive = false;

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedAccommodationOptionEntity> availabilityGeneratedAccommodationOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedFoodOptionEntity> availabilityGeneratedFoodOptionEntities = new LinkedList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTransferOptionEntity> availabilityGeneratedTransferOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTransportationPackageEntity> availabilityGeneratedTransportationPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedGuideOptionEntity> availabilityGeneratedGuideOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedSpotEntryOptionEntity> availabilityGeneratedSpotEntryOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTourPackageInclusiveOptionEntity> availabilityGeneratedTourPackageInclusiveOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedTourPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTourPackageAllOptionEntity> availabilityGeneratedTourPackageAllOptionEntities = new ArrayList<>();

}