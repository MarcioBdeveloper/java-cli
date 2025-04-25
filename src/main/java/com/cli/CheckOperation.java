package com.cli;

import com.cli.dto.OperationBuy;
import com.cli.dto.OperationSell;
import com.cli.dto.OperationInput;
import com.cli.dto.OperationOutput;
import com.cli.util.CalculationLogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckOperation {
    public static final String ZERO_VALUE_TAX = "0.0";
    public static final String PERCENT_OF_TAX_PAID = "0.20";
    public static final int MINIMUM_PROFIT_FOR_CALCULATION = 20000;
    public static final String OPERATION_BUY = "buy";
    public static final String OPERATION_SELL = "sell";

    public static List<OperationOutput> check(List<OperationInput> inputData) {
        List<OperationOutput> outputData = new ArrayList<>();
        BigDecimal accumulatedLoss = BigDecimal.ZERO;

        var operationBuy = new OperationBuy(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, outputData);

        for(OperationInput input : inputData){
            if(input.getOperation().equals(OPERATION_BUY)) {
                checkOperationBuy(input, operationBuy, outputData);
            }

            if(input.getOperation().equals(OPERATION_SELL)) {
                var operationSell = new OperationSell(operationBuy, input);
                accumulatedLoss = checkOperationSell(operationSell, accumulatedLoss, outputData);
            }
        }
        return outputData;
    }

    private static void checkOperationBuy(OperationInput input, OperationBuy operationBuy, List<OperationOutput> outputData) {
        if(operationBuy.getNewWeightedAveragePrice().compareTo(BigDecimal.ZERO) == 0){
            operationBuy.setNewWeightedAveragePrice(input.getUnitCost());
        }
        operationBuy.setSharesPurchased(BigDecimal.valueOf(input.getQuantity()));

        /*Performs weighted average price calculation*/
        operationBuy.setNewWeightedAveragePrice(
                CalculationLogic.calcWeightedAveragePrice(
                        operationBuy.getCurrentNumberOfShares(),
                        operationBuy.getNewWeightedAveragePrice(),
                        operationBuy.getSharesPurchased(),
                        input.getUnitCost()
                )
        );
        operationBuy.setCurrentNumberOfShares(
                CalculationLogic.calcSum(operationBuy.getCurrentNumberOfShares(), new BigDecimal(input.getQuantity()))
        );
        var outputTax = new OperationOutput(new BigDecimal(ZERO_VALUE_TAX));
        outputData.add(outputTax);
    }

    private static BigDecimal checkOperationSell(OperationSell operationSell, BigDecimal accumulatedLoss, List<OperationOutput> outputData) {
        operationSell.getOperationBuy().setCurrentNumberOfShares(
                CalculationLogic.calcSubtract(operationSell.getOperationBuy().getCurrentNumberOfShares(), BigDecimal.valueOf(operationSell.getInput().getQuantity()))
        );
        BigDecimal percentageValue = new BigDecimal(ZERO_VALUE_TAX);
        /*Tax applies if the sales price was higher than the weighted average price*/
        if (operationSell.getInput().getUnitCost().compareTo(operationSell.getOperationBuy().getNewWeightedAveragePrice()) > 0) {

            BigDecimal totalValueOfTransaction = CalculationLogic.calcOperationTotalValue(operationSell.getInput().getQuantity(), operationSell.getInput().getUnitCost());
            BigDecimal profit = CalculationLogic.calcAccumulatedValue(operationSell.getInput().getQuantity(), operationSell.getOperationBuy().getNewWeightedAveragePrice(), operationSell.getInput().getUnitCost());

            /*Check accumulated loss and determines if apply tax*/
            if(accumulatedLoss.compareTo(BigDecimal.ZERO) > 0 && totalValueOfTransaction.compareTo(new BigDecimal(MINIMUM_PROFIT_FOR_CALCULATION)) != 0) {

                if(accumulatedLoss.compareTo(profit) > 0) {
                    accumulatedLoss = CalculationLogic.calcSubtract(accumulatedLoss, profit);
                    profit = BigDecimal.ZERO;
                }else if(accumulatedLoss.compareTo(profit) < 0) {
                    profit = CalculationLogic.calcSubtract(profit, accumulatedLoss);
                    accumulatedLoss = BigDecimal.ZERO;
                } else if(accumulatedLoss.compareTo(profit) == 0) {
                    accumulatedLoss = BigDecimal.ZERO;
                    profit = BigDecimal.ZERO;
                }
            }
            /*Calculate tax if profit is bigger than 20k and not have a loss*/
            if(totalValueOfTransaction.compareTo(new BigDecimal(MINIMUM_PROFIT_FOR_CALCULATION)) > 0 && accumulatedLoss.compareTo(BigDecimal.ZERO) == 0) {
                percentageValue = CalculationLogic.calcMultiply(profit, new BigDecimal(PERCENT_OF_TAX_PAID));
            }

        } else {
            /*Calculate for deduct loss*/
            accumulatedLoss = CalculationLogic.calcAccumulatedValue(operationSell.getInput().getQuantity(), operationSell.getOperationBuy().getNewWeightedAveragePrice(), operationSell.getInput().getUnitCost());
        }
        var outputTax = new OperationOutput(percentageValue);
        outputData.add(outputTax);
        return accumulatedLoss;
    }
}
