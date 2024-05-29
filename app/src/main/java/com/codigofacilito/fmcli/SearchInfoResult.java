package com.codigofacilito.fmcli;

import java.io.File;

public sealed abstract class SearchInfoResult permits InfoSuccess, InfoError{
    public final File file;

    public SearchInfoResult(File file){
        this.file = file;
    }
}

final class InfoSuccess extends SearchInfoResult{
    public InfoSuccess(File file) {
        super(file);
    }
}

final class InfoError extends SearchInfoResult{
    public InfoError() {
        super(null);
    }
}