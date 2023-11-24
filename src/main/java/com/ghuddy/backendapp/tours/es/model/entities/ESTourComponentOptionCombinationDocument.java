package com.ghuddy.backendapp.tours.es.model.entities;

import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageAllOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageInclusiveOptionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(indexName = "available_tour_package_component_options_combination")
public class ESTourComponentOptionCombinationDocument {
    @Id
    @Field(name = "option_combination_id")
    private Long optionCombinationId;
    @Field(name = "available_tour_package_id", type = FieldType.Long)
    private Long availableTourPackageId;
    @Field(name = "accommodation_option_id", type = FieldType.Long)
    private Long accommodationOptionId = Long.valueOf(0);
    @Field(name = "food_option_id", type = FieldType.Long)
    private Long foodOptionId = Long.valueOf(0);
    @Field(name = "transfer_option_id", type = FieldType.Long)
    private Long transferOptionId = Long.valueOf(0);
    @Field(name = "transportation_option_id", type = FieldType.Long)
    private Long transportationOptionId = Long.valueOf(0);
    @Field(name = "guide_option_id", type = FieldType.Long)
    private Long guideOptionId = Long.valueOf(0);
    @Field(name = "spot_entry_option_id", type = FieldType.Long)
    private Long spotEntryOptionId = Long.valueOf(0);
    @Field(name = "black_price", type = FieldType.Double)
    private BigDecimal ghuddyWebsiteBlackPrice;
    @Field(name = "red_price", type = FieldType.Double)
    private BigDecimal ghuddyWebsiteRedPrice;

    public ESTourComponentOptionCombinationDocument(AvailabilityGeneratedTourPackageAllOptionEntity availabilityGeneratedTourPackageAllOptionEntity) {
        this.optionCombinationId = availabilityGeneratedTourPackageAllOptionEntity.getId();
        this.availableTourPackageId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedTourPackageEntity().getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedAccommodationOptionEntity() != null)
            this.accommodationOptionId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedAccommodationOptionEntity().getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedFoodOptionEntity() != null)
            this.foodOptionId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedFoodOptionEntity().getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedTransferOptionEntity() != null)
            this.transferOptionId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedTransferOptionEntity().getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedTransportationPackageEntity() != null)
            this.transportationOptionId = availabilityGeneratedTourPackageAllOptionEntity.getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedGuideOptionEntity() != null)
            this.guideOptionId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedGuideOptionEntity().getId();

        if (availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedSpotEntryOptionEntity() != null)
            this.spotEntryOptionId = availabilityGeneratedTourPackageAllOptionEntity.getAvailabilityGeneratedSpotEntryOptionEntity().getId();

        this.ghuddyWebsiteBlackPrice = availabilityGeneratedTourPackageAllOptionEntity.getGhuddyWebsiteBlackPrice();
        this.ghuddyWebsiteRedPrice = availabilityGeneratedTourPackageAllOptionEntity.getGhuddyWebsiteRedPrice();
    }

    public ESTourComponentOptionCombinationDocument(AvailabilityGeneratedTourPackageInclusiveOptionEntity availabilityGeneratedTourPackageInclusiveOptionEntity) {
        this.availableTourPackageId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedTransferOptionEntity().getId();
        this.optionCombinationId = availabilityGeneratedTourPackageInclusiveOptionEntity.getId();

        if (availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedAccommodationOptionEntity() != null)
            this.accommodationOptionId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedAccommodationOptionEntity().getId();

        if (availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedFoodOptionEntity() != null)
            this.foodOptionId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedFoodOptionEntity().getId();

        if (availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedTransferOptionEntity() != null)
            this.transferOptionId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedTransferOptionEntity().getId();

        if (availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedGuideOptionEntity() != null)
            this.guideOptionId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedGuideOptionEntity().getId();

        if (availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedSpotEntryOptionEntity() != null)
            this.spotEntryOptionId = availabilityGeneratedTourPackageInclusiveOptionEntity.getAvailabilityGeneratedSpotEntryOptionEntity().getId();

        this.ghuddyWebsiteBlackPrice = availabilityGeneratedTourPackageInclusiveOptionEntity.getGhuddyWebsiteBlackPrice();
        this.ghuddyWebsiteRedPrice = availabilityGeneratedTourPackageInclusiveOptionEntity.getGhuddyWebsiteRedPrice();
    }
}
