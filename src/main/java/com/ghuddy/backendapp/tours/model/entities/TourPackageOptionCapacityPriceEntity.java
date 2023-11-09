package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tour_package_option_capacity_price")
public class TourPackageOptionCapacityPriceEntity extends BaseEntity {

    @Column(name = "RACK_RATE")
    BigDecimal rackRate; // dotComBlackPrice

    @Column(name = "CORPORATE_RATE")
    BigDecimal corporateRate;

    @Column(name = "CORPORATE_RATE_DISCOUNT_PERCENT")
    BigDecimal corporateDiscountPercent;

    @Column(name = "GHUDDY_COMMISSION_PERCENT")
    BigDecimal ghuddyCommissionPercent; //ghuddy commission Percent

    @Column(name = "GHUDDY_COMMISSION_AMOUNT")
    BigDecimal ghuddyCommissionAmount; //ghuddy commission amount

    @Column(name = "SHURJO_COMMISSION_AMOUNT")
    BigDecimal shurjoCommissionAmount; //shurjo commission Percent

    @Column(name = "SHURJO_COMMISSION_PERCENT")
    BigDecimal shurjoCommissionPercent; //shurjo commission Percent

    @Column(name = "GHUDDY_WEBSITE_BLACK_PRICE")
    BigDecimal ghuddyWebSiteBlackPrice;

    @Column(name = "GHUDDY_SUBSIDY_AMOUNT")
    BigDecimal ghuddySubsidyAmount;

    @Column(name = "GHUDDY_SUBSIDY_PERCENT")
    BigDecimal ghuddySubsidyPercent;

    @Column(name = "GHUDDY_WEBSITE_RED_PRICE")
    BigDecimal ghuddyWebsiteRedPrice;

    @Column(name = "TOTAL_DISCOUNT_PERCENT")
    BigDecimal totalDiscountPercent;

    @Column(name = "total_shurjo_commission_amount")
    BigDecimal totalShurjoCommissionAmount;

    @Column(name = "total_ghuddy_commission_amount")
    BigDecimal totalGhuddyCommissionAmount;

    @Column(name = "total_ghuddy_commission_percent")
    BigDecimal totalGhuddyCommissionPercent;

    @Column(name = "TOTAL_SEATS")
    Long totalSeats;

    @Column(name = "BOOKABLE_SEATS")
    Long bookableSeats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_option_id")
    TourPackageOptionEntity tourPackageOptionEntity;

    @OneToMany(mappedBy = "capacityPrice", cascade = CascadeType.ALL)
    List<TourPackageOptionCapacityPriceFilterEntity> capacityPriceFilters;
}
