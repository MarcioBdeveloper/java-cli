package com.cli.dto;

public class OperationSell {
    OperationBuy operationBuy;
    OperationInput input;

    public OperationSell() {
    }

    public OperationSell(OperationBuy operationBuy, OperationInput input) {
        this.operationBuy = operationBuy;
        this.input = input;
    }

    public OperationBuy getOperationBuy() {
        return operationBuy;
    }

    public void setOperationBuy(OperationBuy operationBuy) {
        this.operationBuy = operationBuy;
    }

    public OperationInput getInput() {
        return input;
    }

    public void setInput(OperationInput input) {
        this.input = input;
    }
}
