package com.ghuddy.backendapp.tours.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tour_package_accommodation")
public class TourPackageAccommodationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_shareable")
    private Boolean isShareable;

    @NotNull
    @Column(name = "suitable_for_persons", nullable = false)
    private Integer suitableForPersons;

    @Column(name = "bed_count")
    private Integer bedCount;

    @Size(max = 100)
    @Column(name = "bed_configuration", length = 100)
    private String bedConfiguration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_category_id", nullable = false)
    private TourRoomCategoryEntity tourRoomCategoryEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id", nullable = false)
    private TourRoomTypeEntity tourRoomTypeEntity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackageEntity tourPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private TourAccommodationEntity tourAccommodationEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourPackageAccommodationEntity that = (TourPackageAccommodationEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}