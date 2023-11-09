package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageOptionCapacityPriceDailyData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TourPackageOptionCapacityPriceDailyDataListResponse extends BaseResponse {

    List<TourPackageOptionCapacityPriceDailyData> availabilityPriceList;
    long noOfEntries;

    public TourPackageOptionCapacityPriceDailyDataListResponse(List<TourPackageOptionCapacityPriceDailyData> availabilityPriceList, String requestId) {
        this.availabilityPriceList = availabilityPriceList;
        this.noOfEntries = availabilityPriceList.size();
        setRequestId(requestId);
    }
}
