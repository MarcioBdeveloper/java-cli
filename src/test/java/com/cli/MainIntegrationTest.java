package com.cli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainIntegrationTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private Path tempFile;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() throws IOException {
        System.setIn(systemIn);
        System.setOut(systemOut);
        if (tempFile != null) {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testMainCase1() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase2() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":10000.0},{\"tax\":0.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase3() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 3000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":1000.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase4() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase5() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":25.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 5000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":10000.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase6() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":3000.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase7() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":2.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 2000}, {\"operation\":\"sell\", \"unit-cost\":25.00, \"quantity\": 1000}, {\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 4350}, {\"operation\":\"sell\", \"unit-cost\":30.00, \"quantity\": 650}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":3000.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":3700.0},{\"tax\":0.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase8() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}, {\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":50.00, \"quantity\": 10000}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":80000.0},{\"tax\":0.0},{\"tax\":60000.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainCase9() throws IOException {
        String input = "[{\"operation\": \"buy\", \"unit-cost\": 5000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 4000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 15000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 4000.00, \"quantity\": 2}, {\"operation\": \"buy\", \"unit-cost\": 23000.00, \"quantity\": 2}, {\"operation\": \"sell\", \"unit-cost\": 20000.00, \"quantity\": 1}, {\"operation\": \"sell\", \"unit-cost\": 12000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 15000.00, \"quantity\": 3}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":1000.0},{\"tax\":2400.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainWithEmptyInput() throws IOException {
        String input = "";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainWithMultipleInputs() throws IOException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5}, {\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 5}]\n" +
                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]\n" +
                "[{\"operation\": \"buy\", \"unit-cost\": 5000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 4000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 15000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 4000.00, \"quantity\": 2}, {\"operation\": \"buy\", \"unit-cost\": 23000.00, \"quantity\": 2}, {\"operation\": \"sell\", \"unit-cost\": 20000.00, \"quantity\": 1}, {\"operation\": \"sell\", \"unit-cost\": 12000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 15000.00, \"quantity\": 3}]";

        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        Main.main(new String[0]);

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0}]\n[{\"tax\":0.0},{\"tax\":10000.0},{\"tax\":0.0}]\n[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":1000.0},{\"tax\":2400.0}]\n";
        if (testOut != null){
            assertEquals(expectedOutput, testOut.toString());
        }
    }

    @Test
    public void testMainWithFile() throws IOException {
        String fileContent = "[{\"operation\":\"buy\", \"unit-cost\":20.00, \"quantity\": 10}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5}, {\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 5}]\n" +
                "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 10000}, {\"operation\":\"sell\", \"unit-cost\":20.00, \"quantity\": 5000}, {\"operation\":\"sell\", \"unit-cost\":5.00, \"quantity\": 5000}]\n" +
                "[{\"operation\": \"buy\", \"unit-cost\": 5000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 4000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 15000.00, \"quantity\": 5}, {\"operation\": \"buy\", \"unit-cost\": 4000.00, \"quantity\": 2}, {\"operation\": \"buy\", \"unit-cost\": 23000.00, \"quantity\": 2}, {\"operation\": \"sell\", \"unit-cost\": 20000.00, \"quantity\": 1}, {\"operation\": \"sell\", \"unit-cost\": 12000.00, \"quantity\": 10}, {\"operation\": \"sell\", \"unit-cost\": 15000.00, \"quantity\": 3}]";

        tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, fileContent.getBytes());
        Main.main(new String[]{tempFile.toString()});

        String expectedOutput = "[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0}]\n[{\"tax\":0.0},{\"tax\":10000.0},{\"tax\":0.0}]\n[{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":0.0},{\"tax\":1000.0},{\"tax\":2400.0}]\n";
        if (testOut != null) {
            assertEquals(expectedOutput, testOut.toString());
        }
    }

}
