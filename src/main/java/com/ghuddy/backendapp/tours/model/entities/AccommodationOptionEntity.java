package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "active")
    private Boolean active = true;
    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "total_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;

}
