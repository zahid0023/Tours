package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TourItineraryData {
    @JsonProperty("activity_name")
    private String activityName;
    @JsonProperty("day_number")
    private Integer dayNumber;
    @JsonProperty("start_time")
    private LocalTime startTime;
    @JsonProperty("end_time")
    private LocalTime endTime;

    public TourItineraryData(TourItineraryEntity tourItineraryEntity) {
        this.activityName = tourItineraryEntity.getActivity().getActivityName();
        this.dayNumber = tourItineraryEntity.getDayNumber();
        this.startTime = tourItineraryEntity.getStartTime();
        this.endTime = tourItineraryEntity.getEndTime();
    }
}
