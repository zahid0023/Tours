package com.ghuddy.backendapp.tours.model.data.availability;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TourPackageOptionCapacityPriceData {
    @Schema(description = "The tour package id")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The tour package option id")
    @JsonProperty("tour_package_option_id")
    private Long tourPackageOptionId;
    @Schema(description = "The tour package option capacity price id")
    @JsonProperty("tour_package_option_capacity_price_id")
    private Long tourPackageOptionCapacityPriceId;
    @Schema(description = "The rack rate")
    @JsonProperty("rack_rate")
    private BigDecimal rackRate;
    @Schema(description = "The corporate rate")
    @JsonProperty("corporate_rate")
    private BigDecimal corporateRate;
    @Schema(description = "The corporate discount percent")
    @JsonProperty("corporate_discount_percent")
    private BigDecimal corporateRateDiscountPercent;
    @Schema(description = "The ghuddy commission percent")
    @JsonProperty("ghuddy_commission_percent")
    private BigDecimal ghuddyCommissionPercent;
    @Schema(description = "The ghuddy commission amount")
    @JsonProperty("ghuddy_commission_amount")
    private BigDecimal ghuddyCommissionAmount;
    @Schema(description = "The shurjo commission amount")
    @JsonProperty("shurjo_commission_amount")
    private BigDecimal shurjoCommissionAmount;
    @Schema(description = "The shurjo commission percent")
    @JsonProperty("shurjo_commission_percent")
    private BigDecimal shurjoCommissionPercent;
    @Schema(description = "The ghuddy website black price")
    @JsonProperty("ghuddy_website_black_price")
    private BigDecimal ghuddyWebsiteBlackPrice;
    @Schema(description = "The ghuddy subsidy amount")
    @JsonProperty("ghuddy_subsidy_amount")
    private BigDecimal ghuddySubsidyAmount;
    @Schema(description = "The ghuddy subsidy percent")
    @JsonProperty("ghuddy_subsidy_percent")
    private BigDecimal ghuddySubsidyPercent;
    @Schema(description = "The ghuddy web site red price")
    @JsonProperty("ghuddy_website_red_price")
    private BigDecimal ghuddyWebsiteRedPrice;
    @Schema(description = "The total discount percent")
    @JsonProperty("total_discount_percent")
    private BigDecimal totalDiscountPercent;
    @Schema(description = "The total shurjo commission amount")
    @JsonProperty("total_shurjo_commission_amount")
    private BigDecimal totalShurjoCommissionAmount;
    @Schema(description = "The total ghuddy commission amount")
    @JsonProperty("total_ghuddy_commission_amount")
    private BigDecimal totalGhuddyCommissionAmount;
    @Schema(description = "The total ghuddy commission percent")
    @JsonProperty("total_ghuddy_commission_percent")
    private BigDecimal totalGhuddyCommissionPercent;
    @Schema(description = "The total seats")
    @JsonProperty("total_seats")
    private Long totalSeats;
    @Schema(description = "The total bookable seats")
    @JsonProperty("bookable_seats")
    private Long bookableSeats;

    public TourPackageOptionCapacityPriceData(TourPackageOptionCapacityPriceEntity tourPackageOptionCapacityPriceEntity) {
        this.tourPackageId = tourPackageOptionCapacityPriceEntity.getTourPackageOptionEntity().getTourPackageEntity().getId();
        this.tourPackageOptionId = tourPackageOptionCapacityPriceEntity.getTourPackageOptionEntity().getId();
        this.tourPackageOptionCapacityPriceId = tourPackageOptionCapacityPriceEntity.getId();
        this.rackRate = tourPackageOptionCapacityPriceEntity.getRackRate();
        this.corporateRate = tourPackageOptionCapacityPriceEntity.getCorporateRate();
        this.corporateRateDiscountPercent = tourPackageOptionCapacityPriceEntity.getCorporateDiscountPercent();
        this.ghuddyCommissionPercent = tourPackageOptionCapacityPriceEntity.getGhuddyCommissionPercent();
        this.ghuddyCommissionAmount = tourPackageOptionCapacityPriceEntity.getGhuddyCommissionAmount();
        this.shurjoCommissionAmount = tourPackageOptionCapacityPriceEntity.getShurjoCommissionAmount();
        this.shurjoCommissionPercent = tourPackageOptionCapacityPriceEntity.getShurjoCommissionPercent();
        this.ghuddyWebsiteBlackPrice = tourPackageOptionCapacityPriceEntity.getGhuddyWebSiteBlackPrice();
        this.ghuddySubsidyAmount = tourPackageOptionCapacityPriceEntity.getGhuddySubsidyAmount();
        this.ghuddySubsidyPercent = tourPackageOptionCapacityPriceEntity.getGhuddySubsidyPercent();
        this.ghuddyWebsiteRedPrice = tourPackageOptionCapacityPriceEntity.getGhuddyWebsiteRedPrice();
        this.totalDiscountPercent = tourPackageOptionCapacityPriceEntity.getTotalDiscountPercent();
        this.totalShurjoCommissionAmount = tourPackageOptionCapacityPriceEntity.getTotalShurjoCommissionAmount();
        this.totalGhuddyCommissionPercent = tourPackageOptionCapacityPriceEntity.getTotalGhuddyCommissionPercent();
        this.totalSeats = tourPackageOptionCapacityPriceEntity.getTotalSeats();
        this.bookableSeats = tourPackageOptionCapacityPriceEntity.getBookableSeats();
    }
}
