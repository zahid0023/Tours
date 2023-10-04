package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourItineraryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TourItineraryData {
    @JsonProperty("tour_activity_id")
    private Long tourActivityId;
    @JsonProperty("tour_activity_name")
    private String tourActivityName;

    public TourItineraryData(TourItineraryEntity tourItineraryEntity) {
        this.tourActivityId = tourItineraryEntity.getActivity().getId();
        this.tourActivityName = tourItineraryEntity.getActivity().getActivityName();
    }
}
