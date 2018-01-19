package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GildedRoseTest {

    public static final String CURRENT_VERIFICATION_FILENAME = "gilded_rose_output.txt";

    /**
     * Generates a new verification file with the current state and compares it against the stage0 verification file.
     * Fails if the files are not the same. Files will only change if a refactoring has changed the program's logic.
     */
    @Test
    public void createVerificationFileShouldCreateTheSameFileFromStage0MissingNames() throws Exception {
        new VerificationCreator().createVerificationFile(CURRENT_VERIFICATION_FILENAME);
        String expectedContent = loadFileContents(VerificationCreator.STAGE0_VERIFICATION_FILENAME, Charset.defaultCharset());
        String actualContent = loadFileContents(CURRENT_VERIFICATION_FILENAME, Charset.defaultCharset());

        assertEquals(expectedContent, actualContent);
    }

    /**
     * Load the entire contents of a file as a String.
     */
    private String loadFileContents(String filepath, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filepath));
        return new String(encoded, encoding);
    }
}
