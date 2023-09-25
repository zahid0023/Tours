package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationBrandRequest extends BaseRequest {
    @Schema(description = "The brand name",required = true, example = "Hyundai")
    @JsonProperty("transportation_brand_name")
    private String brandName;

    @Override
    public void validate() throws AbstractException {

    }
}
