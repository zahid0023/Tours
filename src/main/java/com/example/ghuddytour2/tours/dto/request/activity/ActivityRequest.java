package com.example.ghuddytour2.tours.dto.request.activity;

import com.example.ghuddytour2.tours.dto.request.image.ImageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ActivityRequest {
    @Schema(description = "The ID of the Activity Type", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("activity_type_id")
    private final Long activityTypeID;

    @Schema(description = "The name of the activity", example = "Hiking in Keokradang", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("activity_name")
    private final String activityName;

    @Schema(description = "The short location where the activity will take place", example = "keokradang, bandarban", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("short_location")
    private final String shortLocation;

    @JsonProperty("activity_images")
    List<ImageRequest> activityImages;
}
