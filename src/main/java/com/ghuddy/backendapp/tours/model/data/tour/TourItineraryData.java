package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TourItineraryData {
    @JsonProperty("tour_activity_name")
    private String tourActivityName;
    @JsonProperty("tour_activity_day_number")
    private Integer tourActivityDayNumber;
    @JsonProperty("tour_activity_start_time")
    private LocalTime tourActivityStartTime;
    @JsonProperty("tour_activity_end_time")
    private LocalTime tourActivityEndTime;

    public TourItineraryData(TourItineraryEntity tourItineraryEntity) {
        this.tourActivityName = tourItineraryEntity.getActivity().getActivityName();
        this.tourActivityDayNumber = tourItineraryEntity.getDayNumber();
        this.tourActivityStartTime = tourItineraryEntity.getStartTime();
        this.tourActivityEndTime = tourItineraryEntity.getEndTime();
    }
}
