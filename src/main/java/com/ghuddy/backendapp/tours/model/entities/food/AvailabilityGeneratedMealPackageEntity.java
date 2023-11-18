package com.ghuddy.backendapp.tours.model.entities.food;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_meal_packages")
public class AvailabilityGeneratedMealPackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_package_id")
    private MealPackageEntity mealPackageEntity;

    @Transient
    private Integer mealPackageAvailableInDay;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_food_option_id")
    private AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity;
    @NotNull
    @Column(name = "meal_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal mealPackagePrice;
}