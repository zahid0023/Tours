package com.ghuddy.backendapp.tours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.ActivityTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ActivityTypeListResponse extends BaseResponse {
    @Schema(description = "The list of activity types that will be sent as API response")
    @JsonProperty("activity_types")
    private List<ActivityTypeData> activityTypeDataList;

    public ActivityTypeListResponse(List<ActivityTypeData> activityTypeDataList) {
        this.activityTypeDataList = activityTypeDataList;
    }
}
