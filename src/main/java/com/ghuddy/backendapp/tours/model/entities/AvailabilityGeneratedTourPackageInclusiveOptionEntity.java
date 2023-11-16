package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_tour_package_inclusive_options")
public class AvailabilityGeneratedTourPackageInclusiveOptionEntity extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "availability_generated_tour_package_id", nullable = false)
    private AvailabilityGeneratedTourPackageEntity tourPackageAvailabilityEntity;

    @NotNull
    @Column(name = "ghuddy_platform_calculated_option_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCalculatedRate = BigDecimal.ZERO;
    @Column(name = "merchant_subsidy_amount", precision = 10, scale = 2)
    private BigDecimal merchantSubsidyAmount = BigDecimal.ZERO;
    @Column(name = "net_option_price_after_merchant_subsidy", precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterMerchantSubsidy = BigDecimal.ZERO;
    @Column(name = "ghuddy_platform_commission_amount", precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCommissionAmount = BigDecimal.ZERO;
    @Column(name = "net_option_price_after_ghuddy_commission", precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterGhuddyCommission = BigDecimal.ZERO;
    @Column(name = "ghuddy_website_black_price", precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteBlackPrice = BigDecimal.ZERO;
    @Column(name = "ghuddy_subsidy_amount", precision = 10, scale = 2)
    private BigDecimal ghuddySubsidyAmount = BigDecimal.ZERO;
    @Column(name = "net_option_price_after_ghuddy_subsidy", precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterGhuddySubsidy;
    @Column(name = "ghuddy_website_red_price", precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteRedPrice = BigDecimal.ZERO;
    @Column(name = "payment_gateway_amount", precision = 10, scale = 2)
    private BigDecimal paymentGatewayAmount;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_accommodation_option_id")
    private AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_food_option_id")
    private AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_transfer_option_id")
    private AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_guide_option_id")
    private AvailabilityGeneratedGuideOptionEntity availabilityGeneratedGuideOptionEntity;

    @ManyToOne
    @JoinColumn(name = "availability_generated_spot_entry_option_id")
    private AvailabilityGeneratedSpotEntryOptionEntity availabilityGeneratedSpotEntryOptionEntity;

}