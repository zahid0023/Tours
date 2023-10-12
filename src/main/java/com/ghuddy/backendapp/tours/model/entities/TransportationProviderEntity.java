package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "transportation_provider")
public class TransportationProviderEntity extends BaseEntity {

    @Column(name = "transportation_provider_name")
    private String transportationProviderName;

    @Column(name = "hotline_number", length = 20)
    private String hotlineNumber;

    @Column(name = "rating", precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @OneToMany(mappedBy = "transportationProviderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransportationPackageEntity> tourPackageTransportationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "transportationProviderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransferPackageEntity> transferPackageEntities = new LinkedList<>();

}