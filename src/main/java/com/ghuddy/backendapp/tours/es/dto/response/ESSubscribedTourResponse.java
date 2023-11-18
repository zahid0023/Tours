package com.ghuddy.backendapp.tours.es.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ESSubscribedTourResponse {
    @Schema(description = "The subscribed tour data")
    @JsonProperty("subscribed_tour")
    private ESTourData esTourData;

    public ESSubscribedTourResponse(ESTourData esTourData) {
        this.esTourData = esTourData;
    }
}
