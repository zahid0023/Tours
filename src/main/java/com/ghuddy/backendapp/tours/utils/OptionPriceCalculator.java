package com.ghuddy.backendapp.tours.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@UtilityClass
@Slf4j
public class OptionPriceCalculator {

    // Constants for commission percentages
    private static final BigDecimal GHUDDY_COMMISSION_PERCENTAGE = BigDecimal.valueOf(15); // Replace with your actual percentage
    private static final BigDecimal PAYMENT_GATEWAY_COMMISSION_DIVIDER = BigDecimal.valueOf(0.975); // Replace with your actual multiplier

    public static BigDecimal getBlackPrice(BigDecimal ghuddyPlatformCalculatedOptionPrice, BigDecimal merchantSubsidiaryAmount) {

        // Step 1: Calculate Net Option Price
        BigDecimal netOptionPrice = calculateNetOptionPrice(ghuddyPlatformCalculatedOptionPrice, merchantSubsidiaryAmount);
        log.info("Net Option Price: " + netOptionPrice);

        // Step 2: Calculate Ghuddy Commission Amount
        BigDecimal ghuddyCommissionAmount = calculateGhuddyCommissionAmount(netOptionPrice);
        log.info("Ghuddy Commission Amount: " + ghuddyCommissionAmount);

        // Step 3: Calculate Option Price After Ghuddy Commission
        BigDecimal optionPriceAfterGhuddy = calculateOptionPriceAfterGhuddyCommission(netOptionPrice, ghuddyCommissionAmount);
        log.info("Option Price After Ghuddy Commission: " + optionPriceAfterGhuddy);

        // Step 4: Calculate Black Price (Price user has to pay to cover payment gateway commission)
        BigDecimal blackPrice = calculateBlackPrice(optionPriceAfterGhuddy);
        log.info("Black Price: " + blackPrice);
        return blackPrice;
    }

    private static BigDecimal calculateNetOptionPrice(BigDecimal ghuddyPlatFormCalculatedPrice, BigDecimal merchantSubsidiaryAmount) {
        return ghuddyPlatFormCalculatedPrice.add(merchantSubsidiaryAmount);
    }

    private static BigDecimal calculateGhuddyCommissionAmount(BigDecimal netOptionPrice) {

        // Calculate commission as a percentage of netOptionPrice
        BigDecimal commission = GHUDDY_COMMISSION_PERCENTAGE.divide(BigDecimal.valueOf(100)).multiply(netOptionPrice);

        // Ensure that the result has the desired scale and rounding
        return commission.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal calculateOptionPriceAfterGhuddyCommission(BigDecimal netOptionPrice, BigDecimal ghuddyCommissionAmount) {
        return netOptionPrice.add(ghuddyCommissionAmount);
    }

    private static BigDecimal calculateBlackPrice(BigDecimal optionPriceAfterGhuddyCommission) {
        return optionPriceAfterGhuddyCommission.divide(PAYMENT_GATEWAY_COMMISSION_DIVIDER, 2, BigDecimal.ROUND_HALF_UP);
    }
}
