package com.ghuddy.backendapp.tours.model.entities.food;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "meal_packages")
public class MealPackageEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_type_id")
    private MealTypeEntity mealTypeEntity;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "meal_package_food_item_mapping",
            joinColumns = @JoinColumn(name = "meal_package_id"),
            inverseJoinColumns = @JoinColumn(name = "food_item_id"))
    private List<FoodItemEntity> foodItemEntities = new ArrayList<>();

    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "per_meal_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perMealPrice;

    @OneToMany(mappedBy = "mealPackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedMealPackageEntity> availabilityGeneratedMealPackageEntities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MealPackageEntity that = (MealPackageEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}