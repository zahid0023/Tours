package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEnttiy;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntry;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_tour_package_options_wo_transportation")
public class AvailabilityGeneratedTourPackageOptionsWoTransportationEntity extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_availability_id", nullable = false)
    private TourPackageAvailabilityEntity tourPackageAvailabilityEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_accommodation_option_id")
    private AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_food_option_id")
    private AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_transfer_option_id")
    private AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_guide_option_id")
    private AvailabilityGeneratedGuideOptionEnttiy availabilityGeneratedGuideOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_spot_entry_id")
    private AvailabilityGeneratedSpotEntry availabilityGeneratedSpotEntry;

    @NotNull
    @Column(name = "ghuddy_platform_calculated_option_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCalculatedRate = BigDecimal.ZERO;

    @Column(name = "merchant_subsidy_amount", precision = 10, scale = 2)
    private BigDecimal merchantSubsidyAmount = BigDecimal.ZERO;

    @Column(name = "corporate_rate_discount_percent", precision = 10, scale = 2)
    private BigDecimal corporateRateDiscountPercent = BigDecimal.ZERO;

    @Column(name = "ghuddy_commission_amount", precision = 10, scale = 2)
    private BigDecimal ghuddyCommissionAmount = BigDecimal.ZERO;

    @Column(name = "ghuddy_commission_percent", precision = 10, scale = 2)
    private BigDecimal ghuddyCommissionPercent = BigDecimal.ZERO;

    @Column(name = "shurjo_commission_percent", precision = 10, scale = 2)
    private BigDecimal shurjoCommissionPercent = BigDecimal.ZERO;

    @Column(name = "ghuddy_website_black_price", precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteBlackPrice = BigDecimal.ZERO;

    @Column(name = "ghuddy_subsidy_amount", precision = 10, scale = 2)
    private BigDecimal ghuddySubsidyAmount = BigDecimal.ZERO;

    @Column(name = "ghuddy_subsidy_percent", precision = 10, scale = 2)
    private BigDecimal ghuddySubsidyPercent = BigDecimal.ZERO;

    @Column(name = "ghuddy_website_red_price", precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteRedPrice = BigDecimal.ZERO;

    @Column(name = "total_discount_percent", precision = 10, scale = 2)
    private BigDecimal totalDiscountPercent = BigDecimal.ZERO;

    @Column(name = "net_shurjo_commission_amount", precision = 10, scale = 2)
    private BigDecimal netShurjoCommissionAmount = BigDecimal.ZERO;

    @Column(name = "net_ghuddy_commission_amount", precision = 10, scale = 2)
    private BigDecimal netGhuddyCommissionAmount = BigDecimal.ZERO;

    @Column(name = "net_ghuddy_commission_percent", precision = 10, scale = 2)
    private BigDecimal netGhuddyCommissionPercent = BigDecimal.ZERO;

}