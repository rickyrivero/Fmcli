package com.codigofacilito.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileWritingTest {
    private FileWriting fileWriting;
    private String testDirectory;
    private String testFileName;
    private String testContent;

    @BeforeEach
    public void setUp() {
        fileWriting = new FileWriting();
        testDirectory = "testDir";
        testFileName = "testFile.txt";
        testContent = "This is a test content.";

        // Create a test directory and file
        new File(testDirectory).mkdirs();
        try {
            new File(testDirectory, testFileName).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test directory and files
        new File(testDirectory, testFileName).delete();
        new File(testDirectory).delete();
    }

    @Test
    public void testWriteFile_FileWritten() {
        var result = fileWriting.writeFile(testDirectory, testFileName, testContent);
        assertTrue(result instanceof FileWritten);
        assertTrue(new File(testDirectory, testFileName).exists());

        // Verify file content
        try {
            String content = Files.readString(Path.of(testDirectory, testFileName));
            assertEquals(testContent, content);
        } catch (IOException e) {
            fail("Error al leer el archivo.");
        }
    }

    @Test
    public void testWriteFile_FileWriteError() {
        var result = fileWriting.writeFile("invalidDir", testFileName, testContent);
        assertTrue(result instanceof FileWriteError);
    }
}