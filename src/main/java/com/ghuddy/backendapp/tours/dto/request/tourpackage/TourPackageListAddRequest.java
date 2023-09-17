package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import lombok.Data;

import java.util.List;

@Data
public class TourPackageListAddRequest {
    private Long tourID;
    private List<TourPackageRequest> tourPackages;
}
