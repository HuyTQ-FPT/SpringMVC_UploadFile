package com.example.uploadfile.service;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.Item;

import java.util.List;

public interface FileService {
    void save(FileStore fileStore);

    void listSave(List<FileStore> fileStoreList);

    List<Item> getFile(long folderId);

    void add(FileStore fileStore);

    FileStore getFileById(long id);

    void deleteFile(long id);

    List<FileStore> getFileByFolderId(long folderId);

    FileStore updateNameFolderAndFile(FileStore file);

    void setFileName(String fileName);

    boolean checkFileUploadExist(long preid,String name);

    FileStore getFileUploadExist(long preid,String name);

}
