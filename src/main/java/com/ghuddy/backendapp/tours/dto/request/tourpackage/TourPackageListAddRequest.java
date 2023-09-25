package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageListAddRequest extends BaseRequest {
    @Schema(description = "the id of the tour", required = true, example = "1")
    @JsonProperty("tour_id")
    private Long tourID;
    @Schema(description = "The list of the tour packages that is associated with the tour", required = true)
    @JsonProperty("tour_packages")
    private List<TourPackageRequest> tourPackages;

    @Override
    public void validate() throws AbstractException {

    }
}
