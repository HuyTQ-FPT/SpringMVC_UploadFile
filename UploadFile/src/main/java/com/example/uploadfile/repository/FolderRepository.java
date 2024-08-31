package com.example.uploadfile.repository;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<FolderStore, Long> {

    @Query("SELECT f from FolderStore f WHERE f.preFolder =0")
    List<FolderStore> getRootFolder();

    @Query("SELECT f from FolderStore f WHERE f.preFolder = :fid")
    List<FolderStore> getFolder(long fid);

    @Query("SELECT f.files FROM FolderStore f WHERE f.fid = :folderId")
    List<FileStore> getFilesByFolderId(long folderId);

    @Query("SELECT f FROM FolderStore f WHERE f.preFolder = :preid and f.folderName = :name")
    List<FolderStore> checkFolderExist(String name,long preid);


}
