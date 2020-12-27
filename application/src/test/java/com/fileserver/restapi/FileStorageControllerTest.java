package com.fileserver.restapi;

import com.fileserver.core.FileStorageServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileStorageControllerTest {

    @Mock
    private FileStorageServer fileStorageServer;

    @InjectMocks
    private FileStorageController controller;

    @Test
    void deleteFileOk() {
        when(fileStorageServer.deleteFile("file_to_delete")).thenReturn(true);

        assertThat(controller.deleteFile("file_to_delete").getBody().getMessage()).isEqualTo("File deleted");
    }

    @Test
    void deleteFileKo() {
        when(fileStorageServer.deleteFile("file_to_delete")).thenReturn(false);

        assertThat(controller.deleteFile("file_to_delete").getBody().getMessage()).isEqualTo("File not deleted");
    }

}
