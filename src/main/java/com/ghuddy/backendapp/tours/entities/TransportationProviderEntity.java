package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_provider")
public class TransportationProviderEntity extends BaseEntity {


    @Size(max = 255)
    @Column(name = "transportation_provider_name")
    private String transportationProviderName;

    @Size(max = 20)
    @Column(name = "hotline_number", length = 20)
    private String hotlineNumber;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @OneToMany(mappedBy = "transportationProviderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageTransportationEntity> tourPackageTransportationEntities = new ArrayList<>();

}