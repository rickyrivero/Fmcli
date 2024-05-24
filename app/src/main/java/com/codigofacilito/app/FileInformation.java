package com.codigofacilito.app;

import java.io.File;

public class FileInformation {
    public SearchInfoResult infoFile(String rootDirectory, String name){
        var file = new File(rootDirectory, name);

        if (file.exists() && file.isFile()) {
            System.out.println("File name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Readable: " + file.canRead());
            System.out.println("File size in bytes: " + file.length());
            return new InfoSuccess(file);
        } else {
            System.out.println("El archivo no existe o no es un archivo valido.");
            return new InfoError();
        }
    }
}
