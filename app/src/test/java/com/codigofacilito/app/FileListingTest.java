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
        assertEquals(1, result.files.length);
        assertEquals(testFileName, result.files[0].getName());
    }

    @Test
    public void testGetFilesAndDirectories_NoFilesFound() {
        new File(testDirectory, testFileName).delete(); // Remove the file

        var result = fileListing.getFilesAndDirectories(testDirectory);
        assertTrue(result instanceof NoFilesFound);
    }

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


//Otras pruebas
    /*@Test
    public void testGetFilesReturnsEmptyArrayWhenNoFilesWithExtension() {
        FileListing fileListing;
        var actualFilesFound = fileListing.getFilesByExtension(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli",
                ".mp3");
        assertNotNull(actualFilesFound);
        assertInstanceOf(NoFilesFound.class, actualFilesFound);
        assertNotNull(actualFilesFound.files);
        assertEquals(0, actualFilesFound.files.length);
    }

    @Test
    public void testGetFilesWhenDirectoryDoesNotExist() {
        FileListing fileListing;
        var actualFilesFound = fileListing.getFilesByExtension(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli2",
                "txt");
        assertNotNull(actualFilesFound);
        assertInstanceOf(DirectoryNotFound.class, actualFilesFound);
        assertNull(actualFilesFound.files);
    }

    @Test
    public void testGetFilesAndDirectories_DirectoryNotFound() {
        FileListing fileListing;
        var result = fileListing.getFilesAndDirectories("invalidDir");
        assertTrue(result instanceof DirectoryNotFound);
    }*/
}