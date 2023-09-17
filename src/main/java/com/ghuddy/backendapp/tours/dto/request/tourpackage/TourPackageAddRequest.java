package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import lombok.Data;

@Data
public class TourPackageAddRequest {
    private Long tourID;
    private TourPackageRequest tourPackageRequest;

}
