package com.codigofacilito.fmcli;

import java.io.File;

public sealed abstract class SearchCreateResult permits NoCreatedSearch, CreatedSearch, AlreadyExistsSearch {
    public final File file;

    public SearchCreateResult(File file) {
        this.file = file;
    }
}

final class NoCreatedSearch extends SearchCreateResult {
    public NoCreatedSearch() {
        super(null);
    }
}

final class AlreadyExistsSearch extends SearchCreateResult {
    public AlreadyExistsSearch(File file){
        super(file);
    }
}

final class CreatedSearch extends SearchCreateResult {
    public CreatedSearch(File file){
        super(file);
    }
}
