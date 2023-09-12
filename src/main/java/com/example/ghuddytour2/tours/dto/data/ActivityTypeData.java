package com.example.ghuddytour2.tours.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ActivityTypeData {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The name of the activity type",
            example = "Hiking")
    private final String activityTypeName;
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "The description of the activity",
            example = "Exciting hiking")
    private final String description;
}
