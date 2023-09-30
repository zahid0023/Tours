package com.ghuddy.backendapp.tours.dto.response.tour;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tour.TourData;
import lombok.Data;

import java.util.List;

@Data
public class TourDataResponseList extends BaseResponse {
    private final List<TourData> tourDataList;

    public TourDataResponseList(List<TourData> tourDataList, String requestId) {
        this.tourDataList = tourDataList;
        this.setRequestId(requestId);
    }
}
