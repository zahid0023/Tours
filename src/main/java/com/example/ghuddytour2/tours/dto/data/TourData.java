package com.example.ghuddytour2.tours.dto.data;

import lombok.Data;

@Data
public class TourData {
    private Long tourID;
    private String tourName;
    private String destinationLocation;
    private String numberOfDays;
    private String numberOfNights;
    private String shortAddress;
    private String thumbImageURL;
    private String title;
    private String description;
}
