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
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Fmcli()).execute(args);
        System.out.println(exitCode);
    }

    @Option(names = {"-l", "--list"}, description = "List all the files in current directory")
    private boolean isListing;

    //Return 0 -> SUCCESS
    //Return 1 -> FAILURE
    //Comprobar ejecución en Windows -> echo $LASTEXITCODE
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
