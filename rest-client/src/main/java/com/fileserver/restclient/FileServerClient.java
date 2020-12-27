package com.fileserver.restclient;

import java.util.List;

public interface FileServerClient {
    List<FileDetailsResponse> listFiles();
    void deleteFile(String name);
    String addFile(String name);
}
