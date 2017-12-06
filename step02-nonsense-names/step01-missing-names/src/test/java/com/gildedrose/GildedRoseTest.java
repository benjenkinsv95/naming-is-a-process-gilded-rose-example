package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GildedRoseTest {

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Test
    public void verify() throws Exception {
        GildedRoseVerificationCreation.createVerificationFile("test.txt");
        String expectedContent = readFile("verification.txt", Charset.defaultCharset());
        String actualContent = readFile("test.txt", Charset.defaultCharset());

        assertEquals(expectedContent, actualContent);
    }

}
