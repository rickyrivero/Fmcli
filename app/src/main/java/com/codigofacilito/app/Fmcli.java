package com.codigofacilito.app;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "fmcli",
        mixinStandardHelpOptions = true,
        version = "0.0.1",
        description = "File management easier for your life"
)
public class Fmcli implements Callable<Integer> {
    @Option(names = {"-l", "--list"}, description = "List all the files in current directory")
    private boolean isListing;

    private final FileListing fileListing = new FileListing();

    //Return 0 -> SUCCESS
    //Return 1 -> FAILURE
    //Comprobar ejecución en Windows -> echo $LASTEXITCODE
    @Override
    public Integer call() throws Exception {
        if (isListing){
            var searchFileResult = fileListing.getFilesByExtension(".", ".txt");
            return switch (searchFileResult){
                case NoFilesFound noFilesFound -> 1;
                case DirectoryNotFound directoryNotFound-> -1;
                case FilesFound filesFound -> 0;
                default -> throw new IllegalStateException("Unexpected value: " + searchFileResult);
            };
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Fmcli()).execute(args);
        System.out.println(exitCode);
        System.exit(exitCode);
    }
}
