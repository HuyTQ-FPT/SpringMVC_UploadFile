package com.example.uploadfile.dto;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Getter
public class FolderStoreDto {

    private Long fid;
    private String folderName;
    private LocalDateTime createDate;
    private Long preFolder;
    private List<FolderStoreDto> children = new ArrayList<>();

    // Constructor
    public FolderStoreDto(FolderStore folderStore) {
        this.fid = folderStore.getFid();
        this.folderName = folderStore.getFolderName();
        this.createDate = folderStore.getCreateDate();
        this.preFolder = folderStore.getPreFolder();
    }
}
