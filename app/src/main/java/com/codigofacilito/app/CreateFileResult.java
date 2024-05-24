package com.codigofacilito.app;

import java.io.File;

public sealed abstract class CreateFileResult permits NoFileCreated, FileCreated, FileAlreadyExists {
    public final File file;

    public CreateFileResult(File file) {
        this.file = file;
    }
}

final class NoFileCreated extends CreateFileResult {
    public NoFileCreated() {
        super(null);
    }
}

final class FileAlreadyExists extends CreateFileResult {
    public FileAlreadyExists(File file){
        super(file);
    }
}

final class FileCreated extends CreateFileResult {
    public FileCreated(File file){
        super(file);
    }
}
