package com.example.ghuddytour2.tours.dto.request;

import lombok.Data;

@Data
public class TourPackageAddRequest {
    private Long tourID;
    private TourPackageRequest tourPackageRequest;

}
