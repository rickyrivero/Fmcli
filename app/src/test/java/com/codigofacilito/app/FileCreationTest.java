package com.codigofacilito.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileCreationTest {
    private FileCreation fileCreation = new FileCreation();
    private String testDirectory;
    private String testFileName;

    @BeforeEach
    public void setUp() {
        fileCreation = new FileCreation();
        testDirectory = "testDir";
        testFileName = "testFile.txt";

        // Create a test directory
        new File(testDirectory).mkdirs();
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test directory and files
        File file = new File(testDirectory, testFileName);
        if (file.exists()) {
            file.delete();
        }
        new File(testDirectory).delete();
    }

    @Test
    public void testCreateFile() {
        var result = fileCreation.createFile(
                testDirectory,
                testFileName);
        assertNotNull(result);
        assertTrue(result instanceof CreatedSearch);
        assertTrue(new File(testDirectory, testFileName).exists());
    }

    @Test
    public void testFileAlreadyExists() throws IOException {
        new File(testDirectory, testFileName).createNewFile();
        var result = fileCreation.createFile(testDirectory, testFileName);
        assertTrue(result instanceof AlreadyExistsSearch);
    }

    @Test
    public void testNotCreated() {
        var result = fileCreation.createFile("invalidDir", testFileName);
        assertNotNull(result);
        assertTrue(result instanceof NoCreatedSearch);
    }
}