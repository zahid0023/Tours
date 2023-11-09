package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.TourPackageOptionCapacityPriceFilterData;
import com.ghuddy.backendapp.tours.model.data.availability.TourPackageOptionCapacityPriceData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceFilterEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TourPackageOptionCapacityPriceResponse extends BaseResponse {
    @Schema(description = "The tour package option capacity price data")
    @JsonProperty("tour_package_option_capacity_price")
    private TourPackageOptionCapacityPriceData tourPackageOptionCapacityPriceData;

    public TourPackageOptionCapacityPriceResponse(TourPackageOptionCapacityPriceData tourPackageOptionCapacityPriceData, String requestId) {
        super(requestId);
        this.tourPackageOptionCapacityPriceData = tourPackageOptionCapacityPriceData;
    }
}
