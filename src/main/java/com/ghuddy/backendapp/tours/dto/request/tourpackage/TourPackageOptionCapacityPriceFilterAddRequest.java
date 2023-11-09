package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.enums.AvailabilityPricePolicyTypeEnum;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TourPackageOptionCapacityPriceFilterAddRequest extends BaseRequest {
    @JsonProperty("policy_name")
    String policyName;

    @JsonProperty("tour_date")
    LocalDate tourDate;

    @JsonProperty("capacity_price_id")
    Long capacityPriceId;

    @JsonProperty("policy_type")
    AvailabilityPricePolicyTypeEnum policyType;

    @JsonProperty("delta_rack_price")
    BigDecimal deltaRackPrice; // dotComBlackPrice

    @JsonProperty("delta_corporate_price")
    BigDecimal deltaCorporatePrice;

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

    @Override
    public void validate() throws AbstractException {

    }
}
