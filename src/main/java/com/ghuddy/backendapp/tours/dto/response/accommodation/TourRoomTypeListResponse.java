package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageRoomTypeData;
import lombok.Data;

import java.util.List;

@Data
public class TourRoomTypeListResponse extends BaseResponse {
    @JsonProperty("tour_room_types")
    private List<TourPackageRoomTypeData> tourPackageRoomTypeDataList;

    public TourRoomTypeListResponse(List<TourPackageRoomTypeData> tourPackageRoomTypeDataList, String requestId) {
        this.tourPackageRoomTypeDataList = tourPackageRoomTypeDataList;
        this.setRequestId(requestId);
    }
}
