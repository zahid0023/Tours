package com.example.ghuddytour2.tours.dto.request;

import com.example.ghuddytour2.tours.dto.data.ImageData;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A DTO for the {@link com.example.ghuddytour2.tours.entities.ActivityEntity} entity
 */
@Data
public class ActivityAddRequest implements Serializable {

    @Size(max = 255)
    @NotNull
    private final String activityName;

    @NotNull
    private final Long activityTypeID;
    @Size(max = 255)
    @NotNull
    private final String shortLocation;

    List<ImageData> activityImages;

}