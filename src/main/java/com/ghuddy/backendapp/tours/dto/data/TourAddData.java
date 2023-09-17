package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TourAddData {
    @JsonProperty("tour_id")
    private Long tourId;
    @JsonProperty("tour_name")
    private String tourName;
    @JsonProperty("destination_location")
    private String destinationLocation;
    @JsonProperty("number_of_days")
    private String numberOfDays;
    @JsonProperty("number_of_nights")
    private String numberOfNights;
    @JsonProperty("short_address")
    private String shortAddress;
    @JsonProperty("tour_tag")
    private String tourTag;

}
