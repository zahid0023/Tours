package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class StringUtil {
    public static String slugify(String str1, String str2) {
        // Remove special characters and spaces, and convert to lowercase
        String formattedStr1 = str1.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();
        String formattedStr2 = str2.replaceAll("[^a-zA-Z0-9]+", "-").toLowerCase();

        // Concatenate the formatted strings with a hyphen
        String tag = formattedStr1 + "-" + formattedStr2;

        return tag;
    }

    public static String tourPackageName(String tourName, String packageTypeName) {
        return tourName + " - " + packageTypeName;
    }

    public static String tourName(String destinationLocationName, Integer numberOfDays, Integer numberOfNights) {
        String day = numberOfDays > 1 ? numberOfDays + " Days" : numberOfDays == 1 ? numberOfDays + " Day" : "";
        String night = numberOfNights > 1 ? numberOfNights + " Nights" : numberOfNights == 1 ? numberOfNights + " Night" : "";
        return destinationLocationName + " Tour - " + day + " " + night;
    }

    public static String mealPackageName(TourPackageEntity tourPackageEntity, MealPackageEntity mealPackageEntity, Integer mealPackageCount) {
        String tourPackageName = tourPackageEntity.getTourPackageName();
        String mealPackageTypeName = mealPackageEntity.getMealTypeEntity().getMealTypeName();

        return tourPackageName + " - " + mealPackageTypeName + " - Package#" + mealPackageCount;
    }

    public static Integer mealPackageCount(TourPackageEntity tourPackageEntity, MealPackageEntity mealPackageEntity, JdbcTemplate jdbcTemplate) {
        try {
            return jdbcTemplate.queryForObject(
                    """
                            select count(*)
                            from meal_packages mp
                                     inner join tour_food_option tfo on mp.food_option_id = tfo.id
                                     inner join tour_package tp on tfo.tour_package_id = tp.id
                            where tp.id = ?
                              and mp.meal_type_id = ?
                                                     """,
                    Integer.class,
                    tourPackageEntity.getId(),
                    mealPackageEntity.getMealTypeEntity().getId()
            );
        } catch (EmptyResultDataAccessException ex) {
            return 0;
        }
    }
}

