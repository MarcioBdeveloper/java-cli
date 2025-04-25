package com.cli.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationLogicTest {

    @Test
    public void testCalcWeightedAveragePrice() {
        BigDecimal currentNumberOfShares = new BigDecimal("5");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("20.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("5");
        BigDecimal purchaseValue = new BigDecimal("10.00");
        BigDecimal expectedResult = new BigDecimal("15.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseOne() {
        BigDecimal currentNumberOfShares = new BigDecimal("0");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("10.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("100");
        BigDecimal purchaseValue = new BigDecimal("10.00");
        BigDecimal expectedResult = new BigDecimal("10.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseFourFirstOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("0");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("10.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("10000");
        BigDecimal purchaseValue = new BigDecimal("10.00");
        BigDecimal expectedResult = new BigDecimal("10.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseFourToSecondOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("10000");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("10.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("5000");
        BigDecimal purchaseValue = new BigDecimal("25.00");
        BigDecimal expectedResult = new BigDecimal("15.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseNineToFirstOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("0");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("5000.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("10");
        BigDecimal purchaseValue = new BigDecimal("5000.00");
        BigDecimal expectedResult = new BigDecimal("5000.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseNineToSecondOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("5");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("5000.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("5");
        BigDecimal purchaseValue = new BigDecimal("15000.00");
        BigDecimal expectedResult = new BigDecimal("10000.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseNineToThirdOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("10");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("10000.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("2");
        BigDecimal purchaseValue = new BigDecimal("4000.00");
        BigDecimal expectedResult = new BigDecimal("9000.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseNineToFourthOperationBuy() {
        BigDecimal currentNumberOfShares = new BigDecimal("12");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("9000.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("2");
        BigDecimal purchaseValue = new BigDecimal("23000.00");
        BigDecimal expectedResult = new BigDecimal("11000.00");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcWeightedAveragePriceCaseRoundingDecimals() {
        BigDecimal currentNumberOfShares = new BigDecimal("10");
        BigDecimal currentWeightedAveragePrice = new BigDecimal("20.00");
        BigDecimal numberOfSharesPurchased = new BigDecimal("5");
        BigDecimal purchaseValue = new BigDecimal("10.00");
        BigDecimal expectedResult = new BigDecimal("16.67");

        BigDecimal actualResult = CalculationLogic.calcWeightedAveragePrice(currentNumberOfShares, currentWeightedAveragePrice, numberOfSharesPurchased, purchaseValue);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcOperationTotalValue() {
        int quantity = 100;
        BigDecimal unitCost = new BigDecimal("15.25");
        BigDecimal expectedResult = new BigDecimal("1525.00");

        BigDecimal actualResult = CalculationLogic.calcOperationTotalValue(quantity, unitCost);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalcSum() {
        BigDecimal value1 = new BigDecimal("10.50");
        BigDecimal value2 = new BigDecimal("5.25");
        BigDecimal expectedSum = new BigDecimal("15.75");

        BigDecimal actualSum = CalculationLogic.calcSum(value1, value2);

        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void testCalcSubtract() {
        BigDecimal value1 = new BigDecimal("20.00");
        BigDecimal value2 = new BigDecimal("7.50");
        BigDecimal expectedDifference = new BigDecimal("12.50");

        BigDecimal actualDifference = CalculationLogic.calcSubtract(value1, value2);

        assertEquals(expectedDifference, actualDifference);
    }

    @Test
    public void testCalcMultiply() {
        BigDecimal value1 = new BigDecimal("3.5");
        BigDecimal value2 = new BigDecimal("2.0");
        BigDecimal expectedResult = new BigDecimal("7.0");

        BigDecimal actualProduct = CalculationLogic.calcMultiply(value1, value2);

        assertEquals(expectedResult, actualProduct);

        value1 = new BigDecimal("2.5");
        value2 = new BigDecimal("2.5");
        expectedResult = new BigDecimal("6.3");

        actualProduct = CalculationLogic.calcMultiply(value1, value2);

        assertEquals(expectedResult, actualProduct);

        value1 = new BigDecimal("0");
        value2 = new BigDecimal("1000");
        expectedResult = new BigDecimal("0.0");

        actualProduct = CalculationLogic.calcMultiply(value1, value2);

        assertEquals(expectedResult, actualProduct);
    }

    @Test
    public void testCalcAccumulatedValue() {
        int quantity = 100;
        BigDecimal weightedAveragePrice = new BigDecimal("20.00");
        BigDecimal unitCost = new BigDecimal("18.50");
        BigDecimal expectedLoss = new BigDecimal("150.00");

        BigDecimal actualLoss = CalculationLogic.calcAccumulatedValue(quantity, weightedAveragePrice, unitCost);

        assertEquals(expectedLoss, actualLoss);

        quantity = 50;
        weightedAveragePrice = new BigDecimal("15.50");
        unitCost = new BigDecimal("15.50");
        expectedLoss = new BigDecimal("0.00");

        actualLoss = CalculationLogic.calcAccumulatedValue(quantity, weightedAveragePrice, unitCost);

        assertEquals(expectedLoss, actualLoss);

        quantity = 10;
        weightedAveragePrice = new BigDecimal("100.00");
        unitCost = new BigDecimal("50.00");
        expectedLoss = new BigDecimal("500.00");

        actualLoss = CalculationLogic.calcAccumulatedValue(quantity, weightedAveragePrice, unitCost);

        assertEquals(expectedLoss, actualLoss);
    }
}
