package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListAddRequest {
    private List<TourPackageTypeAddRequest> tourPackageTypes;
}
