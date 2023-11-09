package com.ghuddy.backendapp.tours.model.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TourPackageOptionAvailabilityCapacityPrice {
    private Long packageOptionId;
    private LocalDate date;
    private Long totalSeats;
    private Long bookableSeats;
    private double blackPrice;
    private double redPrice;
    private double discountPercent;
    private String policyType;
}
