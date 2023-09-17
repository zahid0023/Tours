package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

import java.util.List;

@Data
public class AccommodationListAddRequest {
    private List<AccommodationRequest> accommodationRequestList;
}
