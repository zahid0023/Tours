package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.ghuddy.backendapp.tours.model.data.accommodation.TourAccommodationTypeData;
import lombok.Data;

import java.util.List;

@Data
public class TourAccommodationTypeListResponse {
    private List<TourAccommodationTypeData> tourAccommodationTypeDataList;

    public TourAccommodationTypeListResponse(List<TourAccommodationTypeData> tourAccommodationTypeDataList) {
        this.tourAccommodationTypeDataList = tourAccommodationTypeDataList;
    }
}
