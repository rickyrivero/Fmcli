package com.codigofacilito.app;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileListingTest {
    private FileListing fileListing = new FileListing();

    @Test
    public void testGetFiles() {
        var actualFilesFound = fileListing.getFiles("C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli");
        assertNotNull(actualFilesFound);
        assertEquals(10, actualFilesFound.length);
    }

    @Test
    public void testGetFilesByExtension() {
        var actualFilesFound = fileListing.getFilesByExtension(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli",
                "txt");
        assertNotNull(actualFilesFound);
        assertEquals(0, actualFilesFound.length);
    }

    @Test
    public void testGetFilesWhenDirectoryDoesNotExist() {
        var actualFilesFound = fileListing.getFilesByExtension(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli2",
                "txt");
        assertNotNull(actualFilesFound);
        assertEquals(1, actualFilesFound.length);
    }

    @Test
    public void testObtenerArchivos() {
        Files filesFound = fileListing.listarArchivos("C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli");

    }
}