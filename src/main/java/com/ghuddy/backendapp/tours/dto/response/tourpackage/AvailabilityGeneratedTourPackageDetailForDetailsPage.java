package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailabilityGeneratedTourPackageDetailForDetailsPage {
    private LocalDate tourStartDate;
    private Integer totalSeats;
    private Integer bookableSeats;
    private Boolean isAccommodationInclusive;
    private Boolean isFoodInclusive;
    private Boolean isTransferInclusive;
    private Boolean isGuideInclusive;
    private Boolean isSpotEntryInclusive;
    private List<>
}
