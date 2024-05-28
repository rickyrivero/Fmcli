package com.codigofacilito.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileInformationTest {
    private FileInformation fileInformation;
    private String testDirectory;
    private String testFileName;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        fileInformation = new FileInformation();
        testDirectory = "testDir";
        testFileName = "testFile.txt";

        // Crea un directorio de pruebas
        new File(testDirectory).mkdirs();
        testFile = new File(testDirectory, testFileName);
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("This is a test file.");
        }
    }

    @AfterEach
    public void tearDown() {
        // Elimina las creaciones de prueba
        if (testFile.exists()) {
            testFile.delete();
        }
        new File(testDirectory).delete();
    }

    @Test
    public void testInfoFile_FileExists() {
        var result = fileInformation.infoFile(testDirectory, testFileName);
        assertTrue(result instanceof InfoSuccess);

        InfoSuccess infoSuccess = (InfoSuccess) result;
        assertEquals(testFileName, infoSuccess.file.getName());
        assertEquals(testFile.getAbsolutePath(), infoSuccess.file.getAbsolutePath());
        assertTrue(infoSuccess.file.canWrite());
        assertTrue(infoSuccess.file.canRead());
        assertEquals(testFile.length(), infoSuccess.file.length());
    }

    @Test
    public void testInfoFile_FileNotExists() {
        var result = fileInformation.infoFile(testDirectory, "nonExistentFile.txt");
        assertTrue(result instanceof InfoError);
    }
}