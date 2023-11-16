package com.ghuddy.backendapp.tours.model.entities.spot.entry;

import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_package_spot_entry_option")
public class SpotEntryOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "total_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOptionPricePerPerson;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @OneToMany(mappedBy = "spotEntryOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpotEntryPackageEntity> spotEntryPackageEntities = new ArrayList<>();

}