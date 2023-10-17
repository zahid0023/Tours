package com.ghuddy.backendapp.tours.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;

@Data
public class OptionRequest {
    @Schema(description = "The mapping between the night number and where the traveller will stay in that night", required = true)
    @JsonProperty("option")
    HashMap<Integer, String> accommodationOption; // per option

    @Schema(description = "Whether this is the default option for this tour package")
    @JsonProperty("is_default_option")
    private Boolean isDefaultOption;
}
