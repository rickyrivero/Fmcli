package com.codigofacilito.app;

import java.io.File;
import java.io.IOException;

public class FileCreation {
    public CreateFileResult createFile(String rootDirectory, String name){
        var file = new File(rootDirectory, name);
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("El archivo ya existe: " + file.getAbsolutePath());
                return new FileAlreadyExists(file);
            } else {
                System.out.println("El nombre especificado ya existe y no es un archivo.");
                return new NoFileCreated();
            }
        } else {
            var parentDir = file.getParentFile();
            if (parentDir != null && parentDir.exists() && parentDir.isDirectory()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("Archivo creado exitosamente: " + file.getAbsolutePath());
                        return new FileCreated(file);
                    } else {
                        System.out.println("No se pudo crear el archivo por razones desconocidas.");
                        return new NoFileCreated();
                    }
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo: " + e.getMessage());
                    return new NoFileCreated();
                }
            } else {
                System.out.println("El directorio especificado no existe.");
                return new NoFileCreated();
            }
        }
    }
}
