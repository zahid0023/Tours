package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tour_accommodation")
public class TourAccommodationEntity extends BaseEntity {

    @Size(max = 255)
    @NotNull
    @Column(name = "accommodation_name", nullable = false)
    private String accommodationName;

    @Size(max = 255)
    @NotNull
    @Column(name = "accommodation_address", nullable = false)
    private String accommodationAddress;

    @Column(name = "average_rating", precision = 3, scale = 1)
    private BigDecimal averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "accommodation_type_id", nullable = false)
    private TourAccommodationTypeEntity tourAccommodationTypeEntity;

    @OneToMany(mappedBy = "tourAccommodationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = new ArrayList<>();

}