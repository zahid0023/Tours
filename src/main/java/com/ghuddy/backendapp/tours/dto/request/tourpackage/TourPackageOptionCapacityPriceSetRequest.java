package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class TourPackageOptionCapacityPriceSetRequest extends BaseRequest {


    @JsonProperty("rack_price")
    BigDecimal rackPrice; // dotComBlackPrice

    @JsonProperty("corporate_price")
    BigDecimal corporatePrice;

    @JsonProperty("corporate_discount_percent")
    BigDecimal corporateDiscountPercent;

    @JsonProperty("ghuddy_commission_percent")
    BigDecimal ghuddyCommissionPercent; //ghuddy commission Percent

    @JsonProperty("ghuddy_commission_amount")
    BigDecimal ghuddyCommissionAmount; //shurjo commission Percent

    @JsonProperty("shurjo_commission_amount")
    BigDecimal shurjoCommissionAmount; //shurjo commission Percent

    @JsonProperty("shurjo_commission_percent")
    BigDecimal shurjoCommissionPercent; //shurjo commission Percent

    @JsonProperty("ghuddy_website_black_price")
    BigDecimal ghuddyWebsiteBlackPrice;

    @JsonProperty("ghuddy_subsidy_amount")
    BigDecimal ghuddySubsidyAmount;

    @JsonProperty("ghuddy_subsidy_percent")
    BigDecimal ghuddySubsidyPercent;

    @JsonProperty("ghuddy_website_red_price")
    BigDecimal ghuddyWebsiteRedPrice;

    @JsonProperty("total_discount_percent")
    BigDecimal totalDiscountPercent;

    @JsonProperty("total_shurjo_commission_amount")
    BigDecimal totalShurjoCommissionAmount;

    @JsonProperty("total_ghuddy_commission_mount")
    BigDecimal totalGhuddyCommissionAmount;

    @JsonProperty("total_ghuddy_commission_percent")
    BigDecimal totalGhuddyCommissionPercent;

    @JsonProperty("total_seats")
    Long totalSeats;

    @JsonProperty("bookable_seats")
    Long bookableSeats;

    @JsonProperty("tour_package_option_id")
    Long tourPackageOptionId;

    @Override
    public void validate() throws AbstractException {

    }
}
