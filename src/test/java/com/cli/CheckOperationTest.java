package com.cli;

import com.cli.dto.OperationInput;
import com.cli.dto.OperationOutput;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOperationTest {

    @Test
    public void testCheckOperationCase1() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 100));
        inputData.add(new OperationInput("sell", new BigDecimal("15.00"), 50));
        inputData.add(new OperationInput("sell", new BigDecimal("15.00"), 50));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(3, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
    }

    @Test
    public void testCheckOperationCase2() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("5.00"), 5000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(3, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("10000.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
    }

    @Test
    public void testCheckOperationCase3() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("5.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 3000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(3, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("1000.0"), output.get(2).getTax());
    }

    @Test
    public void testCheckOperationCase4() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("buy", new BigDecimal("25.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("15.00"), 10000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(3, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
    }

    @Test
    public void testCheckOperationCase5() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("buy", new BigDecimal("25.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("15.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("25.00"), 5000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(4, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
        assertEquals(new BigDecimal("10000.0"), output.get(3).getTax());
    }

    @Test
    public void testCheckOperationCase6() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("2.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 2000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 2000));
        inputData.add(new OperationInput("sell", new BigDecimal("25.00"), 1000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(5, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(3).getTax());
        assertEquals(new BigDecimal("3000.0"), output.get(4).getTax());
    }

    @Test
    public void testCheckOperationCase7() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("2.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 2000));
        inputData.add(new OperationInput("sell", new BigDecimal("20.00"), 2000));
        inputData.add(new OperationInput("sell", new BigDecimal("25.00"), 1000));
        inputData.add(new OperationInput("buy", new BigDecimal("20.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("15.00"), 5000));
        inputData.add(new OperationInput("sell", new BigDecimal("30.00"), 4350));
        inputData.add(new OperationInput("sell", new BigDecimal("30.00"), 650));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(9, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(3).getTax());
        assertEquals(new BigDecimal("3000.0"), output.get(4).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(5).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(6).getTax());
        assertEquals(new BigDecimal("3700.0"), output.get(7).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(8).getTax());
    }

    @Test
    public void testCheckOperationCase8() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("10.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("50.00"), 10000));
        inputData.add(new OperationInput("buy", new BigDecimal("20.00"), 10000));
        inputData.add(new OperationInput("sell", new BigDecimal("50.00"), 10000));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(4, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("80000.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
        assertEquals(new BigDecimal("60000.0"), output.get(3).getTax());
    }

    @Test
    public void testCheckOperationCase9() {
        List<OperationInput> inputData = new ArrayList();
        inputData.add(new OperationInput("buy", new BigDecimal("5000.00"), 10));
        inputData.add(new OperationInput("sell", new BigDecimal("4000.00"), 5));
        inputData.add(new OperationInput("buy", new BigDecimal("15000.00"), 5));
        inputData.add(new OperationInput("buy", new BigDecimal("4000.00"), 2));
        inputData.add(new OperationInput("buy", new BigDecimal("23000.00"), 2));
        inputData.add(new OperationInput("sell", new BigDecimal("20000.00"), 1));
        inputData.add(new OperationInput("sell", new BigDecimal("12000.00"), 10));
        inputData.add(new OperationInput("sell", new BigDecimal("15000.00"), 3));

        List<OperationOutput> output = CheckOperation.check(inputData);

        assertEquals(8, output.size());
        assertEquals(new BigDecimal("0.0"), output.get(0).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(1).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(2).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(3).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(4).getTax());
        assertEquals(new BigDecimal("0.0"), output.get(5).getTax());
        assertEquals(new BigDecimal("1000.0"), output.get(6).getTax());
        assertEquals(new BigDecimal("2400.0"), output.get(7).getTax());
    }


}
