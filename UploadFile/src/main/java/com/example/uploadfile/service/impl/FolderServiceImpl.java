package com.example.uploadfile.service.impl;


import com.example.uploadfile.dto.FolderStoreDto;
import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.repository.FileUploadRepository;
import com.example.uploadfile.repository.FolderRepository;
import com.example.uploadfile.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FileUploadRepository fileUploadRepository;
    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, FileUploadRepository fileUploadRepository) {
        this.folderRepository = folderRepository;
        this.fileUploadRepository = fileUploadRepository;
    }

    @Override
    public void save(FolderStore folderStore) {
        folderRepository.save(folderStore);
    }

    @Override
    public List<FolderStore> getFolder() {
        List<FolderStore> folderStoreList = folderRepository.findAll();
        return folderStoreList;
    }

    @Override
    public List<FolderStoreDto> createFolderStructure() {

        return null;
    }

    @Override
    public List<FolderStore> getFolderLink(long fid) {
        List<FolderStore> folderStoreList = new ArrayList<>();
        boolean stop = true;
        long rootid = fid;
        while (stop) {
            Optional<FolderStore> fileStore = folderRepository.findById(rootid);
            if (fileStore.isPresent()) {
                rootid = fileStore.get().getPreFolder();
                folderStoreList.add(fileStore.get());
            } else {
                stop = false;
            }
        }
        return folderStoreList;
    }

    @Override
    public List<FolderStoreDto> populateChildFolders(List<FolderStoreDto> parentFolder) {
        return null;
    }

    @Override
    public long getFilePre(long preId) {
        Optional<FolderStore> fileStore = folderRepository.findById(preId);
        return fileStore.isPresent() ? fileStore.get().getPreFolder() : 1L;
    }

    @Override
    public void deleteFolder(long folderId) {
        FolderStore folder = folderRepository.findById(folderId).get();
        List<FolderStore> deleteFolder = new ArrayList<>();
        List<FileStore> deleteFile = new ArrayList<>();

        deleteFolder.add(folder);
        List<FolderStore> temp = folderRepository.getFolder(folder.getFid());

        while (temp != null && !temp.isEmpty()) {
            for (FolderStore folderItem : temp) {
                deleteFolder.add(folderItem);

                List<FileStore> list = fileUploadRepository.getFileByFolderid(folderItem.getFid());
                if (list != null && !list.isEmpty()) {
                    deleteFile.addAll(list);
                }
                
                List<FolderStore> subFolders = folderRepository.getFolder(folderItem.getFid());
                temp = subFolders;
            }
        }
        fileUploadRepository.deleteAll(deleteFile);
        folderRepository.deleteAll(deleteFolder);
    }

    @Override
    public boolean checkFolderExist(String folderName, long preid) {
        return folderRepository.checkFolderExist(folderName,preid).size()>0?true:false;
    }

    @Override
    public FolderStore getByNameAndPreId(String folderName, long preid) {
        return folderRepository.checkFolderExist(folderName,preid).get(0);
    }

    @Override
    public FolderStore getById(long id) {
        return folderRepository.findById(id).get();
    }

}
