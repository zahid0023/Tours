package com.ghuddy.backendapp.tours.dto.request.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SpotEntryAddRequest extends BaseRequest {
    @Schema(description = "The tour package id that belongs to this spot entry")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;

    @Schema(description = "The spot entry request", required = true)
    @JsonProperty("spot_entry")
    private SpotEntryRequest spotEntryRequest;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}
