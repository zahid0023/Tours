package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tour_accommodation_option")
public class AccommodationOptionEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @OneToMany(mappedBy = "accommodationOptionEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AccommodationPackageEntity> accommodationPackageEntities = new LinkedList<>();

    @Column(name = "total_option_price")
    private BigDecimal totalOptionPricePerPerson;

    @OneToMany(mappedBy = "accommodationOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedAccommodationOptionEntity> availabilityGeneratedAccommodationOptionEntities = new ArrayList<>();

    @Column(name = "is_active")
    private Boolean isActive;

}
