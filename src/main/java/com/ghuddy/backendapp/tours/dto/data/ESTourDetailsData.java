package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourData;
import lombok.Data;

@Data
public class ESTourDetailsData {
    @JsonProperty("tour")
    private ESTourData esTourData;
}
