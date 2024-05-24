package com.codigofacilito.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePdfTest {
    private FilePdf filePdf;
    private String testDirectory;
    private String testInputFileName;
    private String testOutputFileName;
    private String testContent;

    @BeforeEach
    public void setUp() {
        filePdf = new FilePdf();
        testDirectory = "testDir";
        testInputFileName = "testFile.txt";
        testOutputFileName = "testFile.pdf";
        testContent = "This is a test content.";

        // Create a test directory and file
        new File(testDirectory).mkdirs();
        try (FileWriter writer = new FileWriter(new File(testDirectory, testInputFileName))) {
            writer.write(testContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test directory and files
        new File(testDirectory, testInputFileName).delete();
        new File(testDirectory, testOutputFileName).delete();
        new File(testDirectory).delete();
    }

    @Test
    public void testTransformToPdf_Success() {
        var result = filePdf.transformToPdf(testDirectory, testInputFileName, testOutputFileName);
        assertTrue(result instanceof PdfFileSuccess);
        assertTrue(new File(testDirectory, testOutputFileName).exists());
    }

    @Test
    public void testTransformToPdf_FileNotFound() {
        var result = filePdf.transformToPdf(testDirectory, "nonexistent.txt", testOutputFileName);
        assertTrue(result instanceof PdfFileError);
    }

    @Test
    public void testTransformToPdf_UnsupportedFormat() {
        // Create a dummy unsupported file
        String unsupportedFileName = "unsupportedFile.xyz";
        try (FileWriter writer = new FileWriter(new File(testDirectory, unsupportedFileName))) {
            writer.write(testContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var result = filePdf.transformToPdf(testDirectory, unsupportedFileName, testOutputFileName);
        assertTrue(result instanceof PdfFileError);
    }
}