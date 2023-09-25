package com.ghuddy.backendapp.tours.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "meal_package")
public class MealPackageEntity extends BaseEntity {


    @Size(max = 255)
    @NotNull
    @Column(name = "meal_package_name", nullable = false)
    private String mealPackageName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_type_id")
    private MealTypeEntity mealTypeEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "meal_package_food_item_mapping",
            joinColumns = @JoinColumn(name = "meal_package_id"),
            inverseJoinColumns = @JoinColumn(name = "food_item_id"))
    private List<FoodItemEntity> foodItemEntities = new ArrayList<>();

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