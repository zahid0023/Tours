package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import lombok.Data;

@Data
public class TourPackageTypeAddRequest {
    private String tourPackageTypeName;
    private String tourPackageTypeDescription;
    private Integer suitableForPersons;
}
