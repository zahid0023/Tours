package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageOptionsWoTransportationEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEnttiy;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntry;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_package_availability")
public class TourPackageAvailabilityEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_accommodation_option_id")
    private AvailabilityGeneratedAccommodationOptionEntity defaultAccommodationOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_food_option_id")
    private AvailabilityGeneratedFoodOptionEntity defaultFoodOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_transfer_option_id")
    private AvailabilityGeneratedTransferOptionEntity defaultTransferOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_guide_option_id")
    private AvailabilityGeneratedGuideOptionEnttiy defaultGuideOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_spot_entry_id")
    private AvailabilityGeneratedSpotEntry defaultSpotEntry;

    @NotNull
    @Column(name = "tour_start_date", nullable = false)
    private LocalDate tourStartDate;

    @NotNull
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @NotNull
    @Column(name = "bookable_seats", nullable = false)
    private Integer bookableSeats;

    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedAccommodationOptionEntity> availabilityGeneratedAccommodationOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedFoodOptionEntity> availabilityGeneratedFoodOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTransferOptionEntity> availabilityGeneratedTransferOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTransportationPackageEntity> availabilityGeneratedTransportationPackageEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedGuideOptionEnttiy> availabilityGeneratedGuideOptionEntities = new ArrayList<>();
    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedSpotEntry> availabilityGeneratedSpotEntries = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageAvailabilityEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTourPackageOptionsWoTransportationEntity> availabilityGeneratedTourPackageOptionsWoTransportationEntities = new ArrayList<>();

}