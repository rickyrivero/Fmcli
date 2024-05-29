package com.codigofacilito.fmcli;

import java.io.File;

public sealed abstract class SearchWriteResult permits FileWriteError,FileWritten {
    public final File file;

    public SearchWriteResult(File file) {
        this.file = file;
    }
}

final class FileWritten extends SearchWriteResult {
    public FileWritten(File file) {
        super(file);
    }
}

final class FileWriteError extends SearchWriteResult {
    public FileWriteError() {
        super(null);
    }
}