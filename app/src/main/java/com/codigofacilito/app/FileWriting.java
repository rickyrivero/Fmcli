package com.codigofacilito.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Escribe un contenido designado en el archivo deseado
 */

public class FileWriting {
    public SearchWriteResult writeFile(String rootDirectory, String name, String content){
        var file = new File(rootDirectory, name);

        //Validar si el archivo existe
        if (file.exists() && file.isFile()) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println("Archivo escrito exitosamente");
                return new FileWritten(file);
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
                return new FileWriteError();
            }
        } else {
            System.out.println("El archivo no existe o no es un archivo válido.");
            return new FileWriteError();
        }
    }
}
