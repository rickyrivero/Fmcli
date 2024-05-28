package com.codigofacilito.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileListingTest {
    private FileListing fileListing;
    private String testDirectory;
    private String testFileName;
    private String testFileExtension;

    @BeforeEach
    public void setUp() {
        fileListing = new FileListing();
        testDirectory = "testDir";
        testFileName = "testFile.txt";
        testFileExtension = ".txt";

        // Crea un directorio de pruebas
        new File(testDirectory).mkdirs();
        try {
            new File(testDirectory, testFileName).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFilesByExtension_FilesFound() {
        var result = fileListing.getFilesByExtension(testDirectory, testFileExtension);
        assertTrue(result instanceof FilesFound);
        assertEquals(1, result.files.length);
        assertEquals(testFileName, result.files[0].getName());
    }

    @Test
    public void testGetFilesAndDirectories_FilesFound() {
        var result = fileListing.getFilesAndDirectories(testDirectory);
        assertTrue(result instanceof FilesFound);
        assertEquals(2, result.files.length);
        assertEquals(testFileName, result.files[0].getName());
    }

    /*@Test
    public void testGetFilesAndDirectories_NoFilesFound() {
        new File(testDirectory, testFileName).delete();

        var result = fileListing.getFilesAndDirectories(testDirectory);
        assertInstanceOf(NoFilesFound.class, result);
    }*/

    @Test
    public void testGetFilesByExtension_DirectoryNotFound() {
        var result = fileListing.getFilesByExtension("invalidDir", testFileExtension);
        assertTrue(result instanceof DirectoryNotFound);
    }

    @Test
    public void testGetFilesAndDirectories_DirectoryNotFound() {
        var result = fileListing.getFilesAndDirectories("invalidDir");
        assertTrue(result instanceof DirectoryNotFound);
    }

    @AfterEach
    public void tearDown() {
        // Elimina las creaciones de prueba
        new File(testDirectory, testFileName).delete();
        new File(testDirectory).delete();
    }
}