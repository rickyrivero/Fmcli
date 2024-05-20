package com.codigofacilito.app;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "fmcli",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "File management easier for your life"
)
public class Fmcli implements Callable<Integer> {
    @Option(names = {"-l", "--list"}, description = "List all the files in current directory")
    private boolean isListing;

    @Option(names = {"-c", "--create"}, description = "Create a new file")
    private boolean isCreating;

    @Option(names = {"-i", "--info"}, description = "Show the information of a file")
    private boolean isInfo;

    private final FileListing fileListing = new FileListing();
    private final FileCreation fileCreation = new FileCreation();
    private final FileInformation fileInformation = new FileInformation();

    //Return 0 -> SUCCESS
    //Return 1 -> FAILURE
    //Comprobar ejecución en Windows -> echo $LASTEXITCODE
    @Override
    public Integer call() throws Exception {
        if (isListing){
            return resolveListing();
        }
        if (isCreating){
            return resolveCreating();
        }
        if (isInfo){
            resolveInformation();
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

    private int resolveCreating(){
        var searchCreateResult = fileCreation.createFile(".", "sin.txt");
        return switch (searchCreateResult){
            case NoFileCreated noFileCreated -> 1;
            case FileAlreadyExists fileAlreadyExists -> -1;
            case FileCreated fileCreated -> 0;
        };
    }

    private int resolveInformation(){
        var searchInfoResult = fileInformation.infoFile(".", "sin.t");
        return switch (searchInfoResult){
            case InfoError infoError -> {
                System.out.println(infoError.getMessage());
                yield 1;
            }
            case InfoSuccess infoSuccess -> {
                infoSuccess.printFileInfo();
                yield 0;
            }
        };
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Fmcli()).execute("-i");
        System.out.println(exitCode);
        System.exit(exitCode);
    }
}
