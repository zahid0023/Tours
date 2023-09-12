package com.example.ghuddytour2.tours.dto.request;

import com.example.ghuddytour2.tours.dto.data.ActivityTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ActivityTypeAddRequest {
    List<ActivityTypeData> activityTypes;
}
