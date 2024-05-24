package com.codigofacilito.app;

import java.io.File;

public sealed abstract class SearchReadResult permits FileReadSuccess, FileReadError {
    public final File file;

    public SearchReadResult(File file) {
        this.file = file;
    }
}

final class FileReadSuccess extends SearchReadResult {
    public final String content;

    public FileReadSuccess(File file, String content) {
        super(file);
        this.content = content;
    }
}

final class FileReadError extends SearchReadResult {
    public FileReadError() {
        super(null);
    }
}
