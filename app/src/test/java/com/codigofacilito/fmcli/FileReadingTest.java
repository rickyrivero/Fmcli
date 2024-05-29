package com.codigofacilito.fmcli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileReadingTest {
    private FileReading fileReading;
    private String testDirectory;
    private String testFileName;
    private String testContent;

    @BeforeEach
    public void setUp() {
        fileReading = new FileReading();
        testDirectory = "testDir";
        testFileName = "testFile.txt";
        testContent = "This is a test content.";

        new File(testDirectory).mkdirs();
        try (FileWriter writer = new FileWriter(new File(testDirectory, testFileName))) {
            writer.write(testContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        new File(testDirectory, testFileName).delete();
        new File(testDirectory).delete();
    }

    @Test
    public void testReadFile_FileReadSuccess() {
        var result = fileReading.readFile(testDirectory, testFileName);
        assertTrue(result instanceof FileReadSuccess);
        assertEquals(testContent, ((FileReadSuccess) result).content);
    }

    @Test
    public void testReadFile_FileReadError() {
        var result = fileReading.readFile("invalidDir", testFileName);
        assertTrue(result instanceof FileReadError);
    }
}