package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourRoomTypeData;
import lombok.Data;

import java.util.List;

@Data
public class TourRoomTypeListResponse extends BaseResponse {
    private List<TourRoomTypeData> tourRoomTypeDataList;

    public TourRoomTypeListResponse(List<TourRoomTypeData> tourRoomTypeDataList) {
        this.tourRoomTypeDataList = tourRoomTypeDataList;
    }
}
