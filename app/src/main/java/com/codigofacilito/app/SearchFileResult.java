package com.codigofacilito.app;

import java.io.File;

public sealed class SearchFileResult permits NoFilesFound, FilesFound, DirectoryNotFound{
    public final File[] files;

    public SearchFileResult(File[] files){
        this.files = files;
    }
}

final class NoFilesFound extends SearchFileResult{
    public NoFilesFound() {
        super(new File[]{});
    }
}

final class DirectoryNotFound extends SearchFileResult{
    public DirectoryNotFound() {
        super(null);
    }
}

final class FilesFound extends SearchFileResult{
    public FilesFound(File[] files) {
        super(files);
    }
}
