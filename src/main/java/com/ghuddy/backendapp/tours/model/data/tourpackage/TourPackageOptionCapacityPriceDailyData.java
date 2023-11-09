package com.ghuddy.backendapp.tours.model.data.tourpackage;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TourPackageOptionCapacityPriceDailyData {
    private Long packageOptionId;
    private Long priceId;
    private LocalDate date;
    private Long totalSeats;
    private Long bookableSeats;
    private double blackPrice;
    private double redPrice;
    private double discountPercent;
    private String policyType;
}
