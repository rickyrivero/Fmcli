package com.codigofacilito.fmcli;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Lista los archivos de un directorio, tiene 2 funciones
 * una para listar el contenido filtrando por tipo de extensión
 * y otra para listar archivos y carpetas en orden
 */

public class FileListing {

    /**
     * Lista los archivos de un directorio por tipo de archivo
     */
    public SearchFileResult getFilesByExtension(String rootDirectory, String extension){
        var directory = new File(rootDirectory);
        System.out.println("Directory: " + directory.getAbsolutePath());

        //Validamos si la ruta es un directorio válido
        if (directory.exists() && directory.isDirectory()) {
            var filesFound = directory.listFiles(pathname ->
                    pathname.getName().endsWith(extension)
            );
            //Validamos si el directorio tiene archivos válidos para ser listados
            if (filesFound != null && filesFound.length > 0){
                //Imprime los valores encontrados
                for (File file : filesFound) {
                    System.out.println("Found file: " + file.getName());
                }
                return new FilesFound(filesFound);
            } else {
                //No se encontraron archivos
                System.out.println("No files found with extension: " + extension);
                return new NoFilesFound();
            }
        } else {
            //Dirección del directorio no válida
            System.out.println("Directory not found: " + rootDirectory);
            return new DirectoryNotFound();
        }
    }

    /**
     * Lista las carpetas y archivos encotradas en la ruta
     * proporcionada de manera ordenada
     */
    public SearchFileResult getFilesAndDirectories(String rootDirectory) {
        File directory = new File(rootDirectory);

        //Validamos si la ruta es un directorio válido
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            //Validamos si el directorio tiene archivos o carpetas válidos para ser listados
            if (files != null && files.length > 0) {
                List<File> directories = new ArrayList<>();
                List<File> fileList = new ArrayList<>();

                //Agregamos los archivos/carpetas encontradas en una lista
                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file);
                    } else {
                        fileList.add(file);
                    }
                }

                //Creamos una lista con los datos para organizar el output
                File[] sortedFiles = new File[directories.size() + fileList.size()];
                int index = 0;

                //Imprimimos las carpetas encontradas
                for (File dir : directories) {
                    sortedFiles[index++] = dir;
                    System.out.println("Directorio: " + dir.getName());
                }

                //Imprimimos los archivos encontrados
                for (File file : fileList) {
                    sortedFiles[index++] = file;
                    System.out.println("Archivo: " + file.getName());
                }

                return new FilesFound(sortedFiles);
            } else {
                System.out.println("La ruta no contiene archivos ni directorios");
                return new NoFilesFound();
            }
        } else {
            System.out.println("La ruta especificada no es un directorio valido");
            return new DirectoryNotFound();
        }
    }

    /*public SearchFileResult getFilesAndDirectories(String rootDirectory){
        File directory = new File(rootDirectory);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("Directorio: " + file.getName());
                    } else {
                        System.out.println("Archivo: " + file.getName());
                    }
                }
                return new FilesFound(files);
            } else {
                System.out.println("La ruta no contiene archivos ni directorios");
                return new NoFilesFound();
            }
        } else {
            System.out.println("La ruta especificada no es un directorio valido");
            return new DirectoryNotFound();
        }
    }*/
}
