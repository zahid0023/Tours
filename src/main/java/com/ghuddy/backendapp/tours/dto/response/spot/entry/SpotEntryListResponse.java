package com.ghuddy.backendapp.tours.dto.response.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SpotEntryListResponse {
    @Schema(description = "The tour package id")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The spot entry list")
    @JsonProperty("spot_entries")
    private List<SpotEntryData> spotEntryDataList;
}
