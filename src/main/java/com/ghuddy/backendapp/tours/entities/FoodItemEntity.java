package com.ghuddy.backendapp.tours.entities;

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
@Table(name = "food_item")
public class FoodItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "food_item_name", nullable = false)
    private String foodItemName;

    @ManyToMany(mappedBy = "foodItemEntities")
    private List<MealPackageEntity> mealPackageEntities = new ArrayList<>();

}