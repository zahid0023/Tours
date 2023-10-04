package com.ghuddy.backendapp.tours.model.data.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.activity.SubscribedTourActivityRequest;
import com.ghuddy.backendapp.tours.model.data.activity.SubscribedTourActivityData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SubscribedTourData {
    @Schema(description = "The name of the tour that the merchant want to subscribe to", example = "1")
    @JsonProperty("tour_name")
    private String tourName;
    @Schema(description = "The tour activities of the tour that merchant must turn into itinerary by providing day number, start time, and end time for each activity")
    @JsonProperty("tour_itinerary")
    private List<SubscribedTourActivityData> subscribedTourActivityList;
    @Schema(description = "The start date of the subscribed tour subscribed by the merchant. It means the merchant will provide the tour starting from this day", example = "21-10-2023")
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The end date of the subscribed tour subscribed by the merchant. It means the merchant will end the tour on this day.", example = "23-10-2023")
    @JsonProperty("tour_end_date")
    private LocalDate tourEndDate;
    @Schema(description = "The tour reporting time on the first day", example = "08:30:00")
    @JsonProperty("tour_reporting_time")
    private LocalTime tourReportingTime;
    @Schema(description = "The tour reporting place on the first day", example = "bandarban bus terminal, bandarban")
    @JsonProperty("tour_reporting_place")
    private String tourReportingPlace;

    @Schema(description = "The list of packages via which this tour will be provided")
    @JsonProperty("tour_packages")
    private List<TourPackageData> tourPackageDataList;

    public SubscribedTourData(SubscribedTourEntity subscribedTourEntity) {
        this.tourName = subscribedTourEntity.getTourEntity().getAddedTourEntity().getTourName();
        this.subscribedTourActivityList = subscribedTourEntity.getSubscribedTourItineraryEntities().stream()
                .map(subscribedTourItineraryEntity -> new SubscribedTourActivityData(subscribedTourItineraryEntity))
                .collect(Collectors.toList());
        this.tourStartDate = subscribedTourEntity.getTourStartDate();
        this.tourEndDate = subscribedTourEntity.getTourEndDate();
        this.tourReportingTime = subscribedTourEntity.getTourReportingTime();
        this.tourReportingPlace = subscribedTourEntity.getTourReportingPlace();
        this.tourPackageDataList = subscribedTourEntity.getTourPackageEntities().stream()
                .map(tourPackageEntity -> new TourPackageData(tourPackageEntity))
                .collect(Collectors.toList());
    }
}
