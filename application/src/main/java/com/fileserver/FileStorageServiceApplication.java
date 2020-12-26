package com.fileserver;

import com.fileserver.core.FileStorageServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FileStorageServiceApplication implements CommandLineRunner {

    @Resource
    FileStorageServer fileStorageServer;

    public static void main(String[] args) {
        SpringApplication.run(FileStorageServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fileStorageServer.deleteAll();
        fileStorageServer.init();
    }
}
