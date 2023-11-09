package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

@Data
public class ESComponentCombinationData {
    @JsonProperty("tour_package_accommodation_option")
    @Field(name = "tour_package_accommodation_option")
    private ESAccommodationOptionData esAccommodationOptionData;
    @JsonProperty("tour_package_food_option")
    @Field(name = "tour_package_food_option")
    private ESFoodOptionData esFoodOptionData;
    @JsonProperty("tour_package_transfer_option")
    @Field(name = "tour_package_transfer_option")
    private ESTransferOptionData esTransferOptionData;
    @JsonProperty("tour_package_total_option_price_per_person")
    @Field(name = "tour_package_option_price_per_person")
    private BigDecimal totalOptionPricePerPerson;

    public ESComponentCombinationData(TourPackageOptionEntity tourPackageOptionEntity) {
        this.setTotalOptionPricePerPerson(BigDecimal.ZERO);

        this.setEsAccommodationOptionData(new ESAccommodationOptionData(tourPackageOptionEntity.getAccommodationOptionEntity()));
        updateOptionPricePerPerson(esAccommodationOptionData);

        this.setEsFoodOptionData(new ESFoodOptionData(tourPackageOptionEntity.getFoodOptionEntity()));
        updateOptionPricePerPerson(esFoodOptionData);

        this.setEsTransferOptionData(new ESTransferOptionData(tourPackageOptionEntity.getTransferOptionEntity()));
        updateOptionPricePerPerson(esFoodOptionData);
    }

    // Overloaded method with generics
    private <T extends ESOptionData> void updateOptionPricePerPerson(T esOptionData) {
        if (esOptionData != null) {
            this.setTotalOptionPricePerPerson(this.getTotalOptionPricePerPerson().add(esOptionData.getTotalOptionPricePerPerson()));
        }
    }

}
