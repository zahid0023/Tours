package com.ghuddy.backendapp.es.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.es.dto.data.ESSubscribedTourData;
import com.ghuddy.backendapp.es.dto.data.ESSubscribedTourItineraryData;
import com.ghuddy.backendapp.es.dto.data.ESTourPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ESSubscribedTourResponse {
    @Schema(description = "The subscribed tour data")
    @JsonProperty("subscribed_tour")
    private ESSubscribedTourData esSubscribedTourData;

    @Schema(description = "The itinerary for this tour")
    @JsonProperty("subscribed_tour_itinerary")
    private List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList;

    @Schema(description = "The availability generated tour packages for this subscribed tour")
    @JsonProperty("subscribed_tour_packages")
    private List<ESTourPackageData> esTourPackageDataList;

    public ESSubscribedTourResponse(ESSubscribedTourData esSubscribedTourData, List<ESSubscribedTourItineraryData> esSubscribedTourItineraryDataList, List<ESTourPackageData> esTourPackageDataList) {
        this.esSubscribedTourData = esSubscribedTourData;
        this.esSubscribedTourItineraryDataList = esSubscribedTourItineraryDataList;
        this.esTourPackageDataList = esTourPackageDataList;
    }
}
