package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.TourPackageOptionCapacityPriceFilterData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TourPackageOptionCapacityPriceFilterListResponse extends BaseResponse {
    List<TourPackageOptionCapacityPriceFilterData> availabilityPriceList;
    long noOfEntries;

    public TourPackageOptionCapacityPriceFilterListResponse(List<TourPackageOptionCapacityPriceFilterData> availabilityPriceList, String requestId) {
        this.availabilityPriceList = availabilityPriceList;
        this.noOfEntries = availabilityPriceList.size();
        setRequestId(requestId);
    }
}
