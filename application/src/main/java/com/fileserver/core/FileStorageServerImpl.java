package com.fileserver.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServerImpl implements FileStorageServer {

    private final static Logger logger = LogManager.getLogger(FileStorageServerImpl.class);
    private final Path root = Paths.get("tmp");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            logger.error("Could not initialize folder for upload!", e);
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            logger.error("Could not store the file.", e);
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                logger.error("Error loading file: " + filename);
                throw new RuntimeException("Could not read the file!");
            }

            return resource;
        } catch (MalformedURLException e) {
            logger.error("Error loading file " + e.getMessage(), e);
            throw new RuntimeException("Error loading file");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public boolean deleteFile(String filename) {
        Resource fileToDelete = load(filename);
        try {
            return fileToDelete.getFile().delete();
        } catch (IOException e) {
            logger.error("Could not delete the file!");
            throw new RuntimeException("Could not delete the file!");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(root, 1)
                    .filter(path -> !path.equals(root))
                    .map(path ->
                            root.relativize(path))
                    ;
        } catch (IOException e) {
            logger.error("Could not load the files", e);
            throw new RuntimeException("Could not load the files!");
        }
    }

}
