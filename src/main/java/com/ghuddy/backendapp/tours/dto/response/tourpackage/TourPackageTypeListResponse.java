package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageTypeData;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListResponse {
    private List<TourPackageTypeData> tourPackageTypeDataList;

    public TourPackageTypeListResponse(List<TourPackageTypeData> tourPackageTypeDataList) {
        this.tourPackageTypeDataList = tourPackageTypeDataList;
    }
}
