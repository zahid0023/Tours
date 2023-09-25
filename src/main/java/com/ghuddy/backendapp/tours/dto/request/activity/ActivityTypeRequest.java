package com.ghuddy.backendapp.tours.dto.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityTypeRequest extends BaseRequest {
    @Schema(required = true,
            description = "The name of the activity type",
            example = "Hiking")
    @JsonProperty("activity_type_name")
    private final String activityTypeName;

    @Schema(required = true,
            description = "The description of the activity",
            example = "Exciting hiking")
    @JsonProperty("description")
    private final String description;

    @Override
    public void validate() throws AbstractException {

    }
}
