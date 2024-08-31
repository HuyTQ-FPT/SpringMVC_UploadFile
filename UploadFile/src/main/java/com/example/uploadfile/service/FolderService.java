package com.example.uploadfile.service;

import com.example.uploadfile.dto.FolderStoreDto;
import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;

import java.util.List;

public interface FolderService {
    void save(FolderStore folderStore);

    List<FolderStore> getFolder();

    List<FolderStoreDto> createFolderStructure();

    List<FolderStore> getFolderLink(long fid);

    List<FolderStoreDto> populateChildFolders(List<FolderStoreDto> parentFolder);

    long getFilePre(long preId);

    void deleteFolder(long folderId);

    boolean checkFolderExist(String folderName, long preid);

    FolderStore getByNameAndPreId(String folderName, long preid);

    FolderStore getById(long id);

}
