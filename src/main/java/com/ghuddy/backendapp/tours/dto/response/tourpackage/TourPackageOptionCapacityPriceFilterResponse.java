package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceFilterEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TourPackageOptionCapacityPriceFilterResponse extends BaseResponse {
    @JsonProperty("tour_package_option_capacity_price_filter_data_id")
    Long tourPackageOptionCapacityPriceFilterDataId;

    @JsonProperty("policy_name")
    String policyName;

    @JsonProperty("tour_date")
    LocalDate tourDate;

    @JsonProperty("capacity_price_id")
    Long capacityPriceId;

    @JsonProperty("policy_type")
    String policyType;

    @JsonProperty("delta_rack_rate")
    BigDecimal deltaRackRate; // dotComBlackPrice

    @JsonProperty("delta_corporate_rate")
    BigDecimal deltaCorporateRate;

    @JsonProperty("delta_corporate_discountPercent_point")
    BigDecimal deltaCorporateDiscountPercentPoint;

    @JsonProperty("delta_ghuddy_commission_percent_point")
    BigDecimal deltaGhuddyCommissionPercentPoint;

    @JsonProperty("delta_ghuddy_commission_amount")
    BigDecimal deltaGhuddyCommissionAmount;

    @JsonProperty("delta_shurjo_commission_percent_point")
    BigDecimal deltaShurjoCommissionPercentPoint; //shurjo commission Percent

    @JsonProperty("delta_shurjo_commission_amount")
    BigDecimal deltaShurjoCommissionAmount; //shurjo commission Percent

    @JsonProperty("delta_ghuddy_website_black_price")
    BigDecimal deltaGhuddyWebSiteBlackPrice;

    @JsonProperty("delta_ghuddy_website_red_price")
    BigDecimal deltaGhuddyWebSiteRedPrice;

    @JsonProperty("delta_total_discount_percent_point")
    BigDecimal deltaTotalDiscountPercentPoint;

    @JsonProperty("delta_ghuddy_subsidy_amount")
    BigDecimal deltaGhuddySubsidyAmount;

    @JsonProperty("delta_ghuddy_subsidy_percent_point")
    BigDecimal deltaGhuddySubsidyPercentPoint;

    @JsonProperty("delta_total_ghuddy_commission_amount")
    BigDecimal deltaTotalGhuddyCommissionAmount; // total ghuddy commission Percent

    @JsonProperty("delta_total_ghuddy_commission_percent_point")
    BigDecimal deltaTotalGhuddyCommissionPercentPoint; //total ghuddy commission Percent

    @JsonProperty("delta_total_shurjo_commission_amount")
    BigDecimal deltaTotalShurjoCommissionAmount; // total ghuddy commission Percent

    @JsonProperty("delta_total_seats")
    Long deltaTotalSeats;

    @JsonProperty("delta_bookable_seats")
    Long deltaBookableSeats;

    public TourPackageOptionCapacityPriceFilterResponse(TourPackageOptionCapacityPriceFilterEntity entity, String requestId) {
        this.tourPackageOptionCapacityPriceFilterDataId = entity.getId();
        this.policyName = entity.getPolicyName();
        this.tourDate = entity.getTourDate();
        this.capacityPriceId = entity.getCapacityPrice().getId();
        this.policyType = entity.getPolicyType().name();
        this.deltaRackRate = entity.getDeltaRackRate(); // dotComBlackPrice
        this.deltaCorporateRate = entity.getDeltaCorporateRate();
        this.deltaCorporateDiscountPercentPoint = entity.getDeltaCorporateDiscountPercentPoint();
        this.deltaGhuddyCommissionPercentPoint = entity.getDeltaGhuddyCommissionPercentPoint();
        this.deltaGhuddyCommissionAmount = entity.getDeltaGhuddyCommissionAmount();
        this.deltaShurjoCommissionPercentPoint = entity.getDeltaShurjoCommissionPercentPoint(); //shurjo commission Percent
        this.deltaShurjoCommissionAmount = entity.getDeltaShurjoCommissionAmount(); //shurjo commission Percent
        this.deltaGhuddyWebSiteBlackPrice = entity.getDeltaGhuddyWebSiteBlackPrice();
        this.deltaGhuddyWebSiteRedPrice = entity.getDeltaGhuddyWebSiteRedPrice();
        this.deltaTotalDiscountPercentPoint = entity.getDeltaTotalDiscountPercentPoint();
        this.deltaGhuddySubsidyAmount = entity.getDeltaGhuddySubsidyAmount();
        this.deltaGhuddySubsidyPercentPoint = entity.getDeltaGhuddySubsidyPercentPoint();
        this.deltaTotalGhuddyCommissionAmount = entity.getDeltaTotalGhuddyCommissionAmount(); // total ghuddy commission Percent
        this.deltaTotalGhuddyCommissionPercentPoint = entity.getDeltaTotalGhuddyCommissionPercentPoint(); //total ghuddy commission Percent
        this.deltaTotalShurjoCommissionAmount = entity.getDeltaTotalShurjoCommissionAmount(); // total ghuddy commission Percent
        this.deltaTotalSeats = entity.getDeltaTotalSeats();
        this.deltaBookableSeats = entity.getDeltaBookableSeats();
        setRequestId(requestId);
    }
}
