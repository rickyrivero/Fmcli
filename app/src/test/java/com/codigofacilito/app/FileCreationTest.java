package com.codigofacilito.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileCreationTest {
    private FileCreation fileCreation = new FileCreation();

    @Test
    public void testCreateFile() {
        var actualFileCreated = fileCreation.createFile(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli",
                "sin.txt");
        assertNotNull(actualFileCreated);
        assertEquals(1, 0);
    }

    @Test
    public void testFileAlreadyExists() {
        var actualFileCreated = fileCreation.createFile(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli",
                "singar.txt");
        assertNotNull(actualFileCreated);
        assertInstanceOf(FileAlreadyExists.class, actualFileCreated);
    }

    @Test
    public void testNotCreated() {
        var actualFileCreated = fileCreation.createFile(
                "C:\\Users\\ricar\\Proyectos_github\\Java\\fmcli\\puta",
                "singar.txt");
        assertNotNull(actualFileCreated);
        assertInstanceOf(NoFileCreated.class, actualFileCreated);
    }
}