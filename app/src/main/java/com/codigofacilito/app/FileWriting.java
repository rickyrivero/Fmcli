package com.codigofacilito.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {
    public SearchWriteResult writeFile(String rootDirectory, String name, String content){
        var file = new File(rootDirectory, name);
        try {
            if (file.exists() && file.isFile()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(content);
                    System.out.println("Archivo escrito exitosamente");
                    return new FileWritten(file);
                }
            } else {
                System.out.println("El archivo no existe o no es un archivo valido.");
                return new FileWriteError("El archivo no existe o no es un archivo valido.");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return new FileWriteError("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
