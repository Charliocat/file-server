package com.fileserver.restclient;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileServerClientImpl implements FileServerClient {

    private static final String API_RESOURCE_PATH = "/v1/files";

    private final String baseUrl;

    public FileServerClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public List<FileDetailsResponse> listFiles() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FileDetailsResponse[]> response
                = restTemplate.getForEntity(baseUrl + API_RESOURCE_PATH, FileDetailsResponse[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public Boolean deleteFile(String name) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(baseUrl + API_RESOURCE_PATH + "/" + name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String addFile(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", getFile(Paths.get(name)));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate
                    .postForEntity(baseUrl + API_RESOURCE_PATH, requestEntity, String.class);
            if (response.getStatusCode().is2xxSuccessful())
                return "File uploaded";
            return "File not uploaded";
        } catch (Exception e) {
            return "File not uploaded";
        }

    }

    private FileSystemResource getFile(Path path) {
        return new FileSystemResource(path);
    }

}
