package com.codigofacilito.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListing {

    public SearchFileResult getFilesByExtension(String rootDirectory, String extension){
        var directory = new File(rootDirectory);
        System.out.println("Directory: " + directory.getAbsolutePath());

        if (directory.exists() && directory.isDirectory()) {
            var filesFound = directory.listFiles(pathname ->
                    pathname.getName().endsWith(extension)
            );
            if (filesFound != null && filesFound.length > 0){
                // Print the file names if found
                for (File file : filesFound) {
                    System.out.println("Found file: " + file.getName());
                }
                return new FilesFound(filesFound);
            } else {
                System.out.println("No files found with extension: " + extension);
                return new NoFilesFound();
            }
        } else {
            System.out.println("Directory not found: " + rootDirectory);
            return new DirectoryNotFound();
        }
    }

    public SearchFileResult getFilesAndDirectories(String rootDirectory) {
        File directory = new File(rootDirectory);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                List<File> directories = new ArrayList<>();
                List<File> fileList = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        directories.add(file);
                    } else {
                        fileList.add(file);
                    }
                }

                File[] sortedFiles = new File[directories.size() + fileList.size()];
                int index = 0;

                for (File dir : directories) {
                    sortedFiles[index++] = dir;
                    System.out.println("Directorio: " + dir.getName());
                }

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
