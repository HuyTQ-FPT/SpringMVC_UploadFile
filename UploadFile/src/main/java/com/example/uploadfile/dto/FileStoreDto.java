package com.example.uploadfile.dto;

import com.example.uploadfile.models.FolderStore;
import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;
import java.time.LocalDateTime;
@Data
@Builder
public class FileStoreDto {

    private Long id;

    private String fileName;

    private byte[] fileData;

    private LocalDateTime createDate;

    private LocalDateTime changeDate;

    private Long folderId;

    private FolderStore folderStore;
}
