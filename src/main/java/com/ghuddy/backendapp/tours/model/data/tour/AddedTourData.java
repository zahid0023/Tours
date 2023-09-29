package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddedTourData {
    @JsonProperty("added_tour_id")
    private Long addedTourId;
    @JsonProperty("added_tour_name")
    private String addedTourName;
    @JsonProperty("destination_location")
    private String destinationLocation;
    @JsonProperty("number_of_days")
    private String numberOfDays;
    @JsonProperty("number_of_nights")
    private String numberOfNights;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("added_tour_tag")
    private String addedTourTag;

}
