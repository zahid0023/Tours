package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

import java.util.List;

@Data
public class AccommodationTypeListAddRequest {
    private List<AccommodationTypeRequest> accommodationTypeRequestList;
}
