package com.ghuddy.backendapp.tours.dto.response.transportation;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.TransportationRouteData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationRouteResponseList extends BaseResponse {
    @Schema(description = "The list of the transportation route")
    private List<TransportationRouteData> transportationRouteDataList;

    public TransportationRouteResponseList(List<TransportationRouteData> transportationRouteDataList) {
        this.transportationRouteDataList = transportationRouteDataList;
    }
}
