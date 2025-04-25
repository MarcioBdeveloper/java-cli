package com.cli;

import com.cli.dto.OperationInput;
import com.cli.dto.OperationOutput;
import com.cli.util.JsonHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = (args.length > 0) ? new BufferedReader(new FileReader(args[0])) : new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                List<OperationInput> inputData = JsonHandler.readJson(line);
                List<OperationOutput> outputData = CheckOperation.check(inputData);
                System.out.println(JsonHandler.writeJson(outputData));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// try (BufferedReader reader = (args.length > 0) ? new BufferedReader(new FileReader(args[0])) : new BufferedReader(new InputStreamReader(System.in))) {
//String line;
//                while ((line = reader.readLine()) != null && !line.isEmpty()) {
//List<OperationInput> inputData = JsonHandler.readJson(line);
//List<OperationOutput> outputData = CheckOperation.check(inputData);
//                    System.out.println(JsonHandler.writeJson(outputData));
//        }
//        } catch (IOException e) {
//        e.printStackTrace();
//            }