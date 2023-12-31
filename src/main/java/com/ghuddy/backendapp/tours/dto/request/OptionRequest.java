package com.ghuddy.backendapp.tours.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OptionRequest {
    @Schema(description = "Whether this option is active or not", required = true, example = "true")
    @JsonProperty("tour_package_option_is_active")
    private Boolean isActive;

    @Schema(description = "Whether this is the default option for this tour package", required = true, example = "false")
    @JsonProperty("tour_package_option_is_default")
    private Boolean isDefault;
}
