package com.codigofacilito.app;

import java.io.File;
import java.io.IOException;

public class FileCreation {
    public SearchCreateResult createFile(String rootDirectory, String name){
        var file = new File(rootDirectory, name);
        if (file.exists()) {
            if (file.isFile()) {
                return new FileAlreadyExists(file);
            } else {
                return new NoFileCreated("El nombre especificado ya existe y no es un archivo.");
            }
        } else {
            var parentDir = file.getParentFile();
            if (parentDir != null && parentDir.exists() && parentDir.isDirectory()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        return new FileCreated(file);
                    } else {
                        return new NoFileCreated("No se pudo crear el archivo por razones desconocidas.");
                    }
                } catch (IOException e) {
                    return new NoFileCreated("Error al crear el archivo: " + e.getMessage());
                }
            } else {
                return new NoFileCreated("El directorio especificado no existe.");
            }
        }
    }
}
