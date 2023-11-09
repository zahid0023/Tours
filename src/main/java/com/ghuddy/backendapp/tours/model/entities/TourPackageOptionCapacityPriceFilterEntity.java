package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.enums.AvailabilityPricePolicyTypeEnum;
import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tour_package_option_capacity_price_filter")
public class TourPackageOptionCapacityPriceFilterEntity extends BaseEntity {

    @Column(name = "POLICY_NAME")
    String policyName;

    @Column(name = "TOUR_DATE")
    LocalDate tourDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "capacity_price_id", referencedColumnName = "ID")
    TourPackageOptionCapacityPriceEntity capacityPrice;

    @Column(name = "POLICY_TYPE")
    @Enumerated(EnumType.STRING)
    AvailabilityPricePolicyTypeEnum policyType;

    @Column(name = "DELTA_RACK_RATE")
    BigDecimal deltaRackRate; // dotComBlackPrice

    @Column(name = "DELTA_CORPORATE_RATE")
    BigDecimal deltaCorporateRate;

    @Column(name = "DELTA_CORPORATE_RATE_DISCOUNT_PERCENT_POINT")
    BigDecimal deltaCorporateDiscountPercentPoint;

    @Column(name = "DELTA_GHUDDY_COMMISSION_PERCENT_POINT")
    BigDecimal deltaGhuddyCommissionPercentPoint;

    @Column(name = "DELTA_GHUDDY_COMMISSION_AMOUNT")
    BigDecimal deltaGhuddyCommissionAmount;

    @Column(name = "DELTA_SHURJO_COMMISSION_PERCENT_POINT")
    BigDecimal deltaShurjoCommissionPercentPoint; //shurjo commission Percent

    @Column(name = "DELTA_SHURJO_COMMISSION_AMOUNT")
    BigDecimal deltaShurjoCommissionAmount; //shurjo commission Percent

    @Column(name = "DELTA_GHUDDY_WEBSITE_BLACK_PRICE")
    BigDecimal deltaGhuddyWebSiteBlackPrice;

    @Column(name = "DELTA_GHUDDY_WEBSITE_RED_PRICE")
    BigDecimal deltaGhuddyWebSiteRedPrice;

    @Column(name = "DELTA_TOTAL_DISCOUNT_PERCENT_POINT")
    BigDecimal deltaTotalDiscountPercentPoint;

    @Column(name = "delta_ghuddy_subsidy_amount")
    BigDecimal deltaGhuddySubsidyAmount;

    @Column(name = "delta_ghuddy_subsidy_percent_point")
    BigDecimal deltaGhuddySubsidyPercentPoint;

    @Column(name = "DELTA_TOTAL_GHUDDY_COMMISSION_AMOUNT")
    BigDecimal deltaTotalGhuddyCommissionAmount; // total ghuddy commission Percent

    @Column(name = "DELTA_TOTAL_GHUDDY_COMMISSION_PERCENT_POINT")
    BigDecimal deltaTotalGhuddyCommissionPercentPoint; //total ghuddy commission Percent

    @Column(name = "DELTA_TOTAL_Shurjo_COMMISSION_AMOUNT")
    BigDecimal deltaTotalShurjoCommissionAmount; // total ghuddy commission Percent

    @Column(name = "DELTA_TOTAL_SEATS")
    Long deltaTotalSeats;

    @Column(name = "DELTA_BOOKABLE_SEATS")
    Long deltaBookableSeats;
}
