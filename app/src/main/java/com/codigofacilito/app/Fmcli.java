package com.codigofacilito.app;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "fmcli",
        mixinStandardHelpOptions = true,
        version = "1.2.0",
        description = "File management easier for your life"
)
public class Fmcli implements Callable<Integer> {

    private final FileListing fileListing = new FileListing();
    private final FileCreation fileCreation = new FileCreation();
    private final FileInformation fileInformation = new FileInformation();
    private final FileWriting fileWriting = new FileWriting();
    private final FileReading fileReading = new FileReading();
    private final FilePdf filePdf = new FilePdf();

    @ArgGroup()
    private FmcliOptions options;

    static class FmcliOptions{
        @Option(names = {"-e", "--extension"}, required = true, description = "Search for files matching the extension in the current directory")
        private boolean isListing;

        @Option(names = {"-c", "--create"}, required = true, description = "Create a new file")
        private boolean isCreating;

        @Option(names = {"-i", "--info"}, required = true, description = "Display file information")
        private boolean isInfo;

        @Option(names = {"-l", "--list"}, required = true, description = "List all the files in current directory")
        private boolean isDirectory;

        @Option(names = {"-w", "--write"}, required = true, description = "Write your files")
        private boolean isWriting;

        @Option(names = {"-r", "--read"}, required = true, description = "Read your files")
        private boolean isReading;

        @Option(names = {"--pdf"}, required = true, description = "Transform your file into a PDF")
        private boolean isPdf;
    }

    //Return 0 -> SUCCESS
    //Return 1/-1 -> FAILURE
    @Override
    public Integer call() throws Exception {
        if (options.isListing){
            return resolveListing();
        }
        if (options.isDirectory){
            return resolveDirectory();
        }
        if (options.isCreating){
            return resolveCreating();
        }
        if (options.isInfo){
            return resolveInformation();
        }
        if (options.isWriting) {
            return resolveWriting();
        }
        if (options.isReading){
            return resolveReading();
        }
        if (options.isPdf){
            return resolvePdf();
        }
        return 0;
    }

    private int resolveListing(){
        var searchFileResult = fileListing.getFilesByExtension(".", ".txt");
        return switch (searchFileResult){
            case NoFilesFound noFilesFound -> 1;
            case DirectoryNotFound directoryNotFound-> -1;
            case FilesFound filesFound -> 0;
        };
    }

    private int resolveDirectory(){
        var searchFileResult = fileListing.getFilesAndDirectories(".");
        return switch (searchFileResult){
            case NoFilesFound noFilesFound -> 1;
            case DirectoryNotFound directoryNotFound-> -1;
            case FilesFound filesFound -> 0;
        };
    }

    private int resolveCreating(){
        var searchCreateResult = fileCreation.createFile(".", "csv.csv");
        return switch (searchCreateResult){
            case NoCreatedSearch noFileCreated -> 1;
            case AlreadyExistsSearch fileAlreadyExists -> -1;
            case CreatedSearch fileCreated -> 0;
        };
    }

    private int resolveInformation(){
        var searchInfoResult = fileInformation.infoFile(".", "sin.txt");
        return switch (searchInfoResult){
            case InfoError infoError -> 1;
            case InfoSuccess infoSuccess -> 0;
        };
    }

    private int resolveWriting(){
        var searchWriteResult = fileWriting.writeFile(".", "sino.txt", "hola como estas");
        return switch (searchWriteResult){
            case FileWriteError fileWriteError -> 1;
            case FileWritten fileWritten -> 0;
        };
    }

    private int resolveReading(){
        var searchReadResult = fileReading.readFile(".", "csv.csv");
        return switch (searchReadResult){
            case FileReadError fileReadError -> 1;
            case FileReadSuccess fileReadSuccess -> 0;
        };
    }

    private int resolvePdf(){
        var pdfFileResult = filePdf.transformToPdf(".", "sino.txt", "prueba.pdf");
        return switch (pdfFileResult){
            case PdfFileError pdfFileError -> 1;
            case PdfFileSuccess pdfFileSuccess -> 0;
        };
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Fmcli()).execute(args);
        System.out.println(exitCode);
        System.exit(exitCode);
    }
}
