package com.cli.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationLogic {

    public static BigDecimal calcWeightedAveragePrice(BigDecimal currentNumberOfShares, BigDecimal currentWeightedAveragePrice, BigDecimal numberOfSharesPurchased, BigDecimal purchaseValue) {
        BigDecimal partOne = currentNumberOfShares.multiply(currentWeightedAveragePrice);
        BigDecimal partTwo = numberOfSharesPurchased.multiply(purchaseValue);
        BigDecimal partThree = currentNumberOfShares.add(numberOfSharesPurchased);
        return partOne.add(partTwo).divide(partThree, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcOperationTotalValue(int quantity, BigDecimal unitCost) {
        return new BigDecimal(quantity).multiply(unitCost).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcSum(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static BigDecimal calcSubtract(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }

    public static BigDecimal calcMultiply(BigDecimal value1, BigDecimal value2) {
        return value1.multiply(value2).setScale(1, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcAccumulatedValue(int quantity, BigDecimal weightedAveragePrice, BigDecimal unitCost) {
        BigDecimal oldValue = CalculationLogic.calcOperationTotalValue(quantity, weightedAveragePrice);
        BigDecimal newValue = CalculationLogic.calcOperationTotalValue(quantity, unitCost);
        return CalculationLogic.calcSubtract(newValue, oldValue).abs();
    }

}
