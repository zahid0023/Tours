package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourLocationEntity;
import lombok.Data;

@Data
public class AddedTourDataOptimized {
    @JsonProperty("added_tour_id")
    private Long addedTourId;
    @JsonProperty("tour_name")
    private String tourName;

    public AddedTourDataOptimized(TourLocationEntity tourLocationEntity) {
        this.addedTourId = tourLocationEntity.getId();
        this.tourName = tourLocationEntity.getTourName();
    }
}
