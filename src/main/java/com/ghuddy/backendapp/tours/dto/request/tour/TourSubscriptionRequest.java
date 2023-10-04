package com.ghuddy.backendapp.tours.dto.request.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class TourSubscriptionRequest {
    @Schema(description = "The id of the tour that the merchant want to subscribe to", required = true, example = "1")
    @JsonProperty("tour_id")
    private Long tourId;
    @Schema(description = "The id of the tour of the merchant who wants to subscribe the tour i.e. provide the tour", required = true, example = "1")
    @JsonProperty("merchant_id")
    private Long merchantId;
    @Schema(description = "The tour activities of the tour that merchant must turn into itinerary by providing day number, start time, and end time for each activity", required = true)
    @JsonProperty("tour_activities")
    private List<SubscribedTourActivityRequest> subscribedTourActivityList;
    @Schema(description = "The start date of the subscribed tour subscribed by the merchant. It means the merchant will provide the tour starting from this day", required = true, example = "2023-10-21")
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The end date of the subscribed tour subscribed by the merchant. It means the merchant will end the tour on this day.", required = true, example = "2023-10-23")
    @JsonProperty("tour_end_date")
    private LocalDate tourEndDate;
    @Schema(description = "The tour reporting time on the first day", required = true, example = "08:30:00")
    @JsonProperty("tour_reporting_time")
    private LocalTime tourReportingTime;
    @Schema(description = "The tour reporting place on the first day", required = true, example = "bandarban bus terminal, bandarban")
    @JsonProperty("tour_reporting_place")
    private String tourReportingPlace;
    @Schema(description = "The list of tour packages via which merchant will provide the tour", required = true)
    @JsonProperty("tour_packages")
    private List<TourPackageRequest> tourPackageRequestList;
}
