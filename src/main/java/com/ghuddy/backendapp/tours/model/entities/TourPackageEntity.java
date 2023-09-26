package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tour_package")
public class TourPackageEntity extends BaseEntity {

    @Column(name = "tour_package_name", nullable = false)
    private String tourPackageName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    private TourEntity tourEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_type_id", nullable = false)
    private TourPackageTypeEntity tourPackageType;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageAccommodationEntity> tourPackageAccommodationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "tourPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageTransportationEntity> tourPackageTransportationEntities = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourPackageEntity that = (TourPackageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}