package com.example.uploadfile.service.impl;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.models.Item;
import com.example.uploadfile.repository.FileUploadRepository;
import com.example.uploadfile.repository.FolderRepository;
import com.example.uploadfile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    @Autowired
    private FolderRepository folderRepository;


    @Autowired
    public FileServiceImpl(FileUploadRepository fileUploadRepository,FolderRepository folderRepository) {
        this.fileUploadRepository = fileUploadRepository;
        this.folderRepository = folderRepository;
    }
    @Override
    public void save(FileStore fileStore) {
        fileUploadRepository.save(fileStore);
    }

    @Override
    public void listSave(List<FileStore> fileStoreList) {
        fileUploadRepository.saveAll(fileStoreList);
    }

    @Override
    public List<Item> getFile(long folderId) {
        List<FolderStore> fileStores = folderRepository.getFolder(folderId);
        List<FileStore> listFile= fileUploadRepository.getFileByFolderid(folderId);
        List<Item> items = new ArrayList<>();
        for (FileStore fileStore : listFile) {
            items.add(Item.builder()
                    .id(fileStore.getId())
                    .itemName(fileStore.getFileName())
                    .createDate(fileStore.getCreateDate())
                    .changeDate(fileStore.getChangeDate())
                    .type("File")
                    .build());
        }
        for (FolderStore fileStore : fileStores) {
            items.add(Item.builder()
                    .id(fileStore.getFid())
                    .itemName(fileStore.getFolderName())
                    .createDate(fileStore.getCreateDate())
                    .changeDate(fileStore.getCreateDate())
                    .type("Folder")
                    .build());
        }
        return (items.isEmpty() || items==null)?new ArrayList<>():items;
    }

    @Override
    public void add(FileStore fileStore) {

    }

    @Override
    public FileStore getFileById(long id) {
        Optional<FileStore> fileStore = fileUploadRepository.findById(id);
        return fileStore.isPresent() ? fileStore.get():new FileStore();

    }

    @Override
    public void deleteFile(long id) {
        fileUploadRepository.deleteById(id);
    }

    @Override
    public List<FileStore> getFileByFolderId(long folderId) {
        List<FileStore> fileStores = fileUploadRepository.findAll();
        return fileStores;
    }

    @Override
    public FileStore updateNameFolderAndFile(FileStore file) {
        FileStore fileStores = fileUploadRepository.save(file);
        return fileStores;
    }

    @Override
    public void setFileName(String fileName) {
        FileStore fileStore = new FileStore();
        setFileName(fileName);
        fileUploadRepository.save(fileStore);
    }

    @Override
    public boolean checkFileUploadExist(long preid, String name) {
        List<FileStore> list = fileUploadRepository.findBynameAndFolderid(preid, name);
        if(list == null || list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    @Override
    public FileStore getFileUploadExist(long preid, String name) {
        List<FileStore> list = fileUploadRepository.findBynameAndFolderid(preid, name);
        if(list == null || list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

}
