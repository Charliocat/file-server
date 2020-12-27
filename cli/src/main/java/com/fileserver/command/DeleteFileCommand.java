package com.fileserver.command;

import com.fileserver.restclient.FileServerClient;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(
        name = "delete",
        description = "delete file"
)
public class DeleteFileCommand implements Runnable {

    private final FileServerClient fileServerClient;

    @CommandLine.Option(names = { "-f", "--file" }, paramLabel = "FILE_NAME", description = "file name to delete", required = true)
    File archive;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true)
    boolean help;

    public DeleteFileCommand(FileServerClient fileServerClient) {
        this.fileServerClient = fileServerClient;
    }

    @Override
    public void run() {
        fileServerClient.deleteFile(archive.getName());
    }

}
