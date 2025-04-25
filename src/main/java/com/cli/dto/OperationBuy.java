package com.cli.dto;

import java.math.BigDecimal;
import java.util.List;

public class OperationBuy {
    BigDecimal newWeightedAveragePrice;
    BigDecimal sharesPurchased;
    BigDecimal currentNumberOfShares;
    List<OperationOutput> outputData;

    public OperationBuy() {
    }

    public OperationBuy(BigDecimal newWeightedAveragePrice, BigDecimal sharesPurchased, BigDecimal currentNumberOfShares, List<OperationOutput> outputData) {
        this.newWeightedAveragePrice = newWeightedAveragePrice;
        this.sharesPurchased = sharesPurchased;
        this.currentNumberOfShares = currentNumberOfShares;
        this.outputData = outputData;
    }

    public BigDecimal getNewWeightedAveragePrice() {
        return newWeightedAveragePrice;
    }

    public void setNewWeightedAveragePrice(BigDecimal newWeightedAveragePrice) {
        this.newWeightedAveragePrice = newWeightedAveragePrice;
    }

    public BigDecimal getSharesPurchased() {
        return sharesPurchased;
    }

    public void setSharesPurchased(BigDecimal sharesPurchased) {
        this.sharesPurchased = sharesPurchased;
    }

    public BigDecimal getCurrentNumberOfShares() {
        return currentNumberOfShares;
    }

    public void setCurrentNumberOfShares(BigDecimal currentNumberOfShares) {
        this.currentNumberOfShares = currentNumberOfShares;
    }

    public List<OperationOutput> getOutputData() {
        return outputData;
    }

    public void setOutputData(List<OperationOutput> outputData) {
        this.outputData = outputData;
    }
}
