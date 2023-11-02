package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_package_option")
public class TourPackageOptionEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_option_id")
    private AccommodationOptionEntity accommodationOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_option_id")
    private FoodOptionEntity foodOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transfer_option_id")
    private TransferOptionEntity transferOptionEntity;

    @Transient
    private TransportationPackageEntity transportationPackageEntity;

}
