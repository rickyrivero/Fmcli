package com.codigofacilito.app;

import java.io.File;
import java.io.IOException;

/**
 * Se encarga de crear un archivo en la ruta dada
 */

public class FileCreation {
    public SearchCreateResult createFile(String rootDirectory, String name){
        var file = new File(rootDirectory, name);
        //Validamos si ya existe el archivo
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("El archivo ya existe: " + file.getAbsolutePath());
                return new AlreadyExistsSearch(file);
            } else {
                System.out.println("El nombre especificado ya existe y no es un archivo.");
                return new NoCreatedSearch();
            }
        } else {
            //Validamos si se puede crear el archivo en la ruta dada
            var parentDir = file.getParentFile();
            if (parentDir != null && parentDir.exists() && parentDir.isDirectory()) {
                try {
                    boolean created = file.createNewFile();
                    if (created) {
                        System.out.println("Archivo creado exitosamente: " + file.getAbsolutePath());
                        return new CreatedSearch(file);
                    } else {
                        System.out.println("No se pudo crear el archivo por razones desconocidas.");
                        return new NoCreatedSearch();
                    }
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo: " + e.getMessage());
                    return new NoCreatedSearch();
                }
            } else {
                System.out.println("El directorio especificado no existe.");
                return new NoCreatedSearch();
            }
        }
    }
}
