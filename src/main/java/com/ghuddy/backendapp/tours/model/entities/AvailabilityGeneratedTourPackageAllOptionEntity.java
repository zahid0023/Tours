package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AvailabilityGeneratedAccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.AvailabilityGeneratedFoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.AvailabilityGeneratedSpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_tour_package_all_options")
public class AvailabilityGeneratedTourPackageAllOptionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_tour_package_id")
    private AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_accommodation_option_id")
    private AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_food_option_id")
    private AvailabilityGeneratedFoodOptionEntity availabilityGeneratedFoodOptionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_transfer_option_id")
    private AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_transportation_package_id")
    private AvailabilityGeneratedTransportationPackageEntity availabilityGeneratedTransportationPackageEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "availability_generated_guide_option_id")
    private AvailabilityGeneratedGuideOptionEntity availabilityGeneratedGuideOptionEntity;

    @NotNull
    @Column(name = "ghuddy_platform_calculated_option_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCalculatedOptionPrice;

    @NotNull
    @Column(name = "merchant_subsidy_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal merchantSubsidyAmount;

    @NotNull
    @Column(name = "net_option_price_after_merchant_subsidy", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterMerchantSubsidy;

    @NotNull
    @Column(name = "ghuddy_platform_commission_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyPlatformCommissionAmount;

    @NotNull
    @Column(name = "net_option_price_after_ghuddy_commission", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterGhuddyCommission;

    @NotNull
    @Column(name = "ghuddy_website_black_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteBlackPrice;

    @NotNull
    @Column(name = "ghuddy_subsidy_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddySubsidyAmount;

    @NotNull
    @Column(name = "net_option_price_after_ghuddy_subsidy", nullable = false, precision = 10, scale = 2)
    private BigDecimal netOptionPriceAfterGhuddySubsidy;

    @NotNull
    @Column(name = "ghuddy_website_red_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ghuddyWebsiteRedPrice;

    @NotNull
    @Column(name = "payment_gateway_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal paymentGatewayAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_spot_entry_option_id")
    private AvailabilityGeneratedSpotEntryOptionEntity availabilityGeneratedSpotEntryOptionEntity;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AvailabilityGeneratedTourPackageAllOptionEntity that = (AvailabilityGeneratedTourPackageAllOptionEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}