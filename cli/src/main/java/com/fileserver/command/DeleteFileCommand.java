package com.fileserver.command;

import com.fileserver.restclient.FileServerClient;
import com.fileserver.restclient.FileServerClientImpl;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(
        name = "delete",
        description = "delete file"
)
public class DeleteFileCommand implements Runnable {

    private final FileServerClient fileServerClient = new FileServerClientImpl("http://localhost:8081");

    @CommandLine.Option(names = { "-f", "--file" }, paramLabel = "ARCHIVE", description = "the archive file")
    File archive;

    @Override
    public void run() {
        fileServerClient.deleteFile(archive.getName());
    }

}
