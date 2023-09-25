package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transportation_brand")
public class TransportationBrandEntity extends BaseEntity {

    @Size(max = 255)
    @NotNull
    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @OneToMany(mappedBy = "transportationBrand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageTransportationEntity> tourPackageTransportationEntities = new ArrayList<>();

}