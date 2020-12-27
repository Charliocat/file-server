package com.fileserver.restapi;

import com.fileserver.core.FileStorageServer;
import com.fileserver.model.FileDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("v1/files")
public class FileStorageController {

    private final FileStorageServer fileStorageServer;

    public FileStorageController(FileStorageServer fileStorageServer) {
        this.fileStorageServer = fileStorageServer;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageServer.save(file);
            String message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            String message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    @GetMapping
    public ResponseEntity<List<FileDetails>> listFiles() {
        List<FileDetails> files = fileStorageServer.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = path.toUri().toString();
            return new FileDetails(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<MessageResponse> deleteFile(@PathVariable String name) {
        if(fileStorageServer.deleteFile(name))
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("File deleted"));
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("File not deleted"));
    }

}
