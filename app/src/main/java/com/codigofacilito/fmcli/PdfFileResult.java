package com.codigofacilito.fmcli;

import java.io.File;

public sealed abstract class PdfFileResult permits PdfFileError,PdfFileSuccess{
    public final File file;

    public PdfFileResult(File file) {
        this.file = file;
    }
}

final class PdfFileSuccess extends PdfFileResult {
    public PdfFileSuccess(File file) {
        super(file);
    }
}

final class PdfFileError extends PdfFileResult {
    public PdfFileError() {
        super(null);
    }
}
