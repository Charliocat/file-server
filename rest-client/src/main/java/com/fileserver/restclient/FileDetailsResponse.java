package com.fileserver.restclient;

public class FileDetailsResponse {

    private String name;
    private String path;

    private FileDetailsResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
