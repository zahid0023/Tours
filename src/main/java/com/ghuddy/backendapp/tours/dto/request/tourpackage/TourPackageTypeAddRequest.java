package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageTypeAddRequest extends BaseRequest {
    @Schema(description = "the package type name", required = true, example = "Couple Package")
    @JsonProperty("tour_package_name")
    private String tourPackageTypeName;
    @Schema(description = "The description of the package type", required = true, example = "A short description")
    @JsonProperty("tour_package_type_description")
    private String tourPackageTypeDescription;
    @Schema(description = "The number of persons that can take part in this tour package", required = true, example = "2")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;

    @Override
    public void validate() throws AbstractException {

    }
}
