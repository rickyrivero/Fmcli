package com.codigofacilito.app;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;

public class FileListing {
    public File[] getFiles(String rootDirectory){
        var directory = new File(rootDirectory);

        return directory.listFiles();

    }

    public File[] getFilesByExtension(String rootDirectory, String extension){
        var directory = new File(rootDirectory);
        return directory.listFiles(pathname -> pathname.getName().endsWith(extension));
    }

    public Files listarArchivos(String rootDirectory){
        File directorio = new File(rootDirectory);
        if (directorio.isDirectory()){
            File[] files = directorio.listFiles();

            if (files != null){
                for (File file : files){
                    if (file.isDirectory()){
                        System.out.println("Directorio: " + file.getName());
                    } else {
                        System.out.println("Archivo: " + file.getName());
                    }
                }
            } else {
                System.out.println("La ruta no contiene archivos ni directorios");
            }
        } else {
            System.out.println("La ruta especificada no es un directorio válido");
        }
        return null;
    }
}
