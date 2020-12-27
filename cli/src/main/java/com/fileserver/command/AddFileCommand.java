package com.fileserver.command;

import com.fileserver.restclient.FileServerClient;
import picocli.CommandLine;

@CommandLine.Command(
        name = "upload-file",
        description = "upload file"
)
public class AddFileCommand implements Runnable {

    private final FileServerClient fileServerClient;

    @CommandLine.Option(names = {"-f", "--file"}, paramLabel = "FILE_PATH", description = "file path", required = true)
    String path;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true) boolean help;

    public AddFileCommand(FileServerClient fileServerClient) {
        this.fileServerClient = fileServerClient;
    }

    @Override
    public void run() {
        System.out.println(fileServerClient.addFile(path));
    }

}
