package com.codigofacilito.app;

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

    public String getFileName() {
        return file.getName();
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public boolean isWritable() {
        return file.canWrite();
    }

    public boolean isReadable() {
        return file.canRead();
    }

    public long getFileSizeInBytes() {
        return file.length();
    }

    public void printFileInfo() {
        System.out.println("File name: " + getFileName());
        System.out.println("Absolute path: " + getAbsolutePath());
        System.out.println("Writable: " + isWritable());
        System.out.println("Readable: " + isReadable());
        System.out.println("File size in bytes: " + getFileSizeInBytes());
    }
}

final class InfoError extends SearchInfoResult{
    public final String message;

    public InfoError(String message) {
        super(null);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}