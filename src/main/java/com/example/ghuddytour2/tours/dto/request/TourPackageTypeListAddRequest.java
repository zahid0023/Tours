package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TourPackageTypeListAddRequest {
    private List<TourPackageTypeAddRequest> tourPackageTypes;
}
