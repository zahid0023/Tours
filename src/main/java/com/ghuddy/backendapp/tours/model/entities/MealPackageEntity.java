package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
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
@Table(name = "tour_package_meal_package")
public class MealPackageEntity extends BaseEntity {

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

    @Column(name = "active")
    private Boolean active;

    @NotNull
    @Column(name = "per_meal_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perMealPrice;

    @NotNull
    @Column(name = "per_person_number_of_meals", nullable = false)
    private Integer perPersonNumberOfMeals;

    @NotNull
    @Column(name = "per_person_total_meal_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal perPersonTotalMealPrice;

    @NotNull
    @Column(name = "is_included", nullable = false)
    private Boolean isIncluded = false;

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