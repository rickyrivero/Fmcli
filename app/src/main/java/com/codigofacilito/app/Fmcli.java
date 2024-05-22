package com.codigofacilito.app;

import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "fmcli",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
        description = "File management easier for your life"
)
public class Fmcli implements Callable<Integer> {

    private final FileListing fileListing = new FileListing();
    private final FileCreation fileCreation = new FileCreation();
    private final FileInformation fileInformation = new FileInformation();

    @ArgGroup()
    private FmcliOptions options;

    static class FmcliOptions{
        @Option(names = {"-e", "--extension"}, required = true, description = "Search for the files that matches the extension in the current directory")
        private boolean isListing;

        @Option(names = {"-c", "--create"}, required = true, description = "Create a new file")
        private boolean isCreating;

        @Option(names = {"-i", "--info"}, required = true, description = "Show the information of a file")
        private boolean isInfo;

        @Option(names = {"-l", "--list"}, required = true, description = "List all the files in current directory")
        private boolean isDirectory;
    }

    //Return 0 -> SUCCESS
    //Return 1 -> FAILURE
    //Comprobar ejecución en Windows -> echo $LASTEXITCODE
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
        var searchCreateResult = fileCreation.createFile(".", "sino.txt");
        return switch (searchCreateResult){
            case NoFileCreated noFileCreated -> 1;
            case FileAlreadyExists fileAlreadyExists -> -1;
            case FileCreated fileCreated -> 0;
        };
    }

    private int resolveInformation(){
        var searchInfoResult = fileInformation.infoFile(".", "sin.txt");
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
        int exitCode = new CommandLine(new Fmcli()).execute(args);
        System.out.println(exitCode);
        System.exit(exitCode);
    }
}
