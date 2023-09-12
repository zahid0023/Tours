package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

@Data
public class TourPackageTypeAddRequest {
    private String tourPackageTypeName;
    private String tourPackageTypeDescription;
    private Integer suitableForPersons;
}
