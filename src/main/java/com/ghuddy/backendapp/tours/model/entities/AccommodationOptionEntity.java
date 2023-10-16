package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tour_accommodation_option")
public class AccommodationOptionEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @OneToMany(mappedBy = "accommodationOptionEntity", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<AccommodationPackageEntity> accommodationPackageEntities = new LinkedList<>();

    @Column(name = "active")
    private Boolean active;
    @Column(name = "is_default")
    private Boolean isDefault;

}
