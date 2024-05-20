package com.codigofacilito.app;

import java.io.File;
import java.io.IOException;

public class FileInformation {
    public SearchInfoResult infoFile(String rootDirectory, String name){
        var file = new File(rootDirectory, name);

        if (file.exists() && file.isFile()) {
            return new InfoSuccess(file);
        } else {
            return new InfoError("El archivo no existe o no es un archivo valido.");
        }
    }
}
