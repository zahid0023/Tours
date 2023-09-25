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
@Table(name = "tour_package_type")
public class TourPackageTypeEntity extends BaseEntity {


    @Size(max = 255)
    @NotNull
    @Column(name = "package_type_name", nullable = false)
    private String packageTypeName;

    @NotNull
    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @NotNull
    @Column(name = "suitable_for", nullable = false)
    private Integer suitableFor;

    @OneToMany(mappedBy = "tourPackageType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageEntity> tourPackageEntities = new ArrayList<>();

}