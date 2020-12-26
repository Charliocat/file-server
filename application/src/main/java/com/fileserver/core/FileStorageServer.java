package com.fileserver.core;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageServer {
    void init();

    void save(MultipartFile file);

    Resource load(String filename);

    void deleteAll();

    boolean deleteFile(String filename);

    Stream<Path> loadAll();
}
