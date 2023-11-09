package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourItineraryEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalTime;
import java.util.List;

@Data
public class ESSubscribedTourItineraryData {
    @JsonProperty("activity_id")
    @Field(name = "activity_id")
    private Long activityId;
    @JsonProperty("activity_name")
    @Field(name = "activity_name")
    private String activityName;
    @JsonProperty("short_address")
    @Field(name = "short_address")
    private String shortAddress;
    @JsonProperty("activity_day_number")
    @Field(name = "day_number")
    private Integer dayNumber;
    @JsonProperty("activity_start_time")
    @Field(name = "start_time")
    private LocalTime startTime;
    @JsonProperty("activity_end_time")
    @Field(name = "end_time")
    private LocalTime endTime;
    @JsonProperty("activity_images")
    @Field(name = "activity_images")
    private List<ESImageData> esImageDataList;

    public ESSubscribedTourItineraryData(SubscribedTourItineraryEntity subscribedTourItineraryEntity) {
        this.activityId = subscribedTourItineraryEntity.getActivityEntity().getId();
        this.activityName = subscribedTourItineraryEntity.getActivityEntity().getActivityName();
        this.shortAddress = subscribedTourItineraryEntity.getActivityEntity().getShortLocation();
        this.dayNumber = subscribedTourItineraryEntity.getActivityDayNumber();
        this.startTime = subscribedTourItineraryEntity.getActivityStartTime();
        this.endTime = subscribedTourItineraryEntity.getActivityEndTime();
        this.esImageDataList = subscribedTourItineraryEntity.getActivityEntity().getActivityImageEntities().stream()
                .map(activityImageEntity -> new ESImageData(activityImageEntity))
                .toList();
    }
}
