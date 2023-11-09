package com.ghuddy.backendapp.tours.dto.data;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TourPackageOptionCapacityData {

    Long TourPackageOptionCapacityId;

    BigDecimal rackPrice; // dotComBlackPrice

    BigDecimal corporatePrice;

    BigDecimal corporateDiscountPercent;

    BigDecimal ghuddyCommissionPercent; //ghuddy commission Percent

    BigDecimal ghuddyCommissionAmount; //shurjo commission Percent

    BigDecimal shurjoCommissionAmount; //shurjo commission Percent

    BigDecimal shurjoCommissionPercent; //shurjo commission Percent

    BigDecimal ghuddyWebsiteBlackPrice;

    BigDecimal ghuddySubsidyAmount;

    BigDecimal ghuddySubsidyPercent;

    BigDecimal ghuddyWebsiteRedPrice;

    BigDecimal totalDiscountPercent;

    BigDecimal totalShurjoCommissionAmount;

    BigDecimal totalGhuddyCommissionAmount;

    BigDecimal totalGhuddyCommissionPercent;

    Long totalSeats;

    Long bookableSeats;

    Long tourPackageOptionId;
}
