package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionDTO;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.TourPackageAccommodationRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class RequestUtil {

    public List<AccommodationOptionRequest> fromAccommodationOptionDTO(AccommodationOptionDTO accommodationOptionDTO) {
        Map<String, AccommodationPackageRequest> accommodationPackageRequestHashMap = accommodationOptionDTO.getAccommodationPackageMapping();
        List<OptionRequest> optionRequestList = accommodationOptionDTO.getAccommodationOptions();

        List<AccommodationOptionRequest> accommodationOptionRequestList = new LinkedList<>();

        optionRequestList.forEach(optionRequest -> {
            Map<String, List<Integer>> accommodationPackageDayNumbers = new HashMap<>();
            List<TourPackageAccommodationRequest> tourPackageAccommodationRequestList = new LinkedList<>();
            optionRequest.getAccommodationOption().forEach((integer, s) -> {
                accommodationPackageDayNumbers.computeIfAbsent(s, key -> new LinkedList<>()).add(integer);
            });
            accommodationPackageDayNumbers.forEach((s, integers) -> {
                tourPackageAccommodationRequestList.add(new TourPackageAccommodationRequest(accommodationPackageRequestHashMap.get(s), integers));
            });
            AccommodationOptionRequest accommodationOptionRequest = new AccommodationOptionRequest();
            accommodationOptionRequest.setIsDefault(optionRequest.getIsDefaultOption());
            //accommodationOptionRequest.setTourPackageAccommodationRequestList(tourPackageAccommodationRequestList);
            accommodationOptionRequestList.add(accommodationOptionRequest);
        });
        return accommodationOptionRequestList;
    }
}




