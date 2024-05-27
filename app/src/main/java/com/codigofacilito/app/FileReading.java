package com.codigofacilito.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Se encarga de leer el contenido de un archivo dado
 */

public class FileReading {
    public SearchReadResult readFile(String rootDirectory, String name) {
        var file = new File(rootDirectory, name);

        //Valida si el archivo existe
        if (file.exists() && file.isFile()) {
            try {
                String content = Files.readString(Path.of(file.getAbsolutePath()));
                System.out.println(content);
                return new FileReadSuccess(file, content);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
                return new FileReadError();
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo valido.");
            return new FileReadError();
        }
    }
}
