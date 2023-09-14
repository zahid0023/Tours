package com.example.ghuddytour2.tours.dto.request.tourpackage;

import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListAddRequest {
    private List<TourPackageTypeAddRequest> tourPackageTypes;
}
