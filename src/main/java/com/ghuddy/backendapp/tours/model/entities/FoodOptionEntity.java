package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tour_food_option")
public class FoodOptionEntity extends BaseEntity {

    @OneToMany(mappedBy = "foodOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "foodOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourPackageOptionEntity> tourPackageOptionEntities = new ArrayList<>();

    @Column(name = "day_number")
    private Integer dayNumber;

    @Column(name = "number_of_meals")
    private Integer numberOfMeals;

    @Column(name = "number_of_breakfast")
    private Integer numberOfBreakfast = 0;

    @Column(name = "number_of_lunch")
    private Integer numberOfLunch = 0;

    @Column(name = "number_of_dinner")
    private Integer numberOfDinner = 0;

    @Column(name = "total_option_price")
    private BigDecimal totalOptionPricePerPerson;

    @Column(name = "active")
    private Boolean active = true;
    @Column(name = "is_default")
    private Boolean isDefault;



}
