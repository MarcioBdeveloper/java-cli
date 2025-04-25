package com.cli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OperationOutput {

    @JsonProperty("tax")
    private BigDecimal tax;

    public OperationOutput() {
    }

    public OperationOutput(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "OperationOutput{" +
                "tax=" + tax +
                '}';
    }
}
