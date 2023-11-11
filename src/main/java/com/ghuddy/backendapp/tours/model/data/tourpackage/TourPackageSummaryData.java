package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourPackageSummaryData {
    @Schema(description = "The id of the tour package type")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The id of the tour package")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The name of the tour package type")
    @JsonProperty("tour_package_type_name")
    private String tourPackageTypeName;
    @Schema(description = "The description of the tour package")
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;
    @Schema(description = "The total options for this tour package")
    @JsonProperty("tour_package_total_options")
    private Integer tourPackageTotalOptions;
    @Schema(description = "The default option price per person")
    @JsonProperty("tour_package_default_option_price_per_person")
    private BigDecimal tourPackageDefaultOptionPricePerPerson;

    public TourPackageSummaryData(TourPackageEntity tourPackageEntity) {
        this.tourPackageTypeId = tourPackageEntity.getTourPackageType().getId();
        this.tourPackageId = tourPackageEntity.getId();
        this.tourPackageTypeName = tourPackageEntity.getTourPackageType().getPackageTypeName();
        this.tourPackageDescription = tourPackageEntity.getDescription();
        //this.tourPackageTotalOptions = tourPackageEntity.getTourPackageOptionEntities().size();
        //this.tourPackageDefaultOptionPricePerPerson = tourPackageEntity.getPackagePricePerPerson();
    }
}
