package com.fileserver.command;

import com.fileserver.restclient.FileDetailsResponse;
import com.fileserver.restclient.FileServerClient;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "list-files",
        description = "list files"
)
public class ListFilesCommand implements Runnable {

    private final FileServerClient fileServerClient;

    public ListFilesCommand(FileServerClient fileServerClient) {
        this.fileServerClient = fileServerClient;
    }

    @Override
    public void run() {
        List<FileDetailsResponse> files = fileServerClient.listFiles();
        for (FileDetailsResponse file : files) {
            System.out.println(file.getName());
        }
    }

}
