package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.example.ghuddytour2.tours.entities.ActivityEntity} entity
 */
@Data
public class ActivityAddRequest implements Serializable {
    @Size(max = 255)
    @NotNull
    private final String activityName;
    @Size(max = 255)
    @NotNull
    private final String shortLocation;
    @NotNull
    private final Long activityTypeID;
}