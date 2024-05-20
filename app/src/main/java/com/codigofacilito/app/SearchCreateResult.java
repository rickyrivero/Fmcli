package com.codigofacilito.app;

import java.io.File;

public sealed abstract class SearchCreateResult permits NoFileCreated, FileCreated, FileAlreadyExists {
    public final File file;

    public SearchCreateResult(File file) {
        this.file = file;
    }
}

final class NoFileCreated extends SearchCreateResult{
    public final String message;
    public NoFileCreated(String message) {
        super(null);
        this.message = message;
    }
}

final class FileAlreadyExists extends SearchCreateResult{
    public FileAlreadyExists(File file){
        super(file);
    }
}

final class FileCreated extends SearchCreateResult{
    public FileCreated(File file){
        super(file);
    }
}
