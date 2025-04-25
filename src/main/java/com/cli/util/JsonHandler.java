package com.cli.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.cli.dto.OperationInput;
import com.cli.dto.OperationOutput;

import java.io.IOException;
import java.util.List;

public class JsonHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<OperationInput> readJson(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<List<OperationInput>>(){});
    }

    public static String writeJson(List<OperationOutput> output) throws IOException {
        return objectMapper.writeValueAsString(output);
    }
}
