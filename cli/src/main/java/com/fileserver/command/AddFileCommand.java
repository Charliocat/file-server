package com.fileserver.command;

import com.fileserver.restclient.FileServerClient;
import com.fileserver.restclient.FileServerClientImpl;
import picocli.CommandLine;

@CommandLine.Command(
        name = "upload-file",
        description = "upload file"
)
public class AddFileCommand implements Runnable {

    private final FileServerClient fileServerClient = new FileServerClientImpl("http://localhost:8081");

    @CommandLine.Option(names = {"-f", "--file"}, paramLabel = "FILE_PATH", description = "file path")
    String path;

    @Override
    public void run() {
        System.out.println(fileServerClient.addFile(path));
    }

}
