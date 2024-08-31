package com.example.uploadfile.repository;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadRepository extends JpaRepository<FileStore,Long> {
    @Query("SELECT f from FileStore f WHERE f.folderId = :fileId")
    List<FileStore> getFileByFolderid(long fileId);

    @Query("SELECT f from FileStore f WHERE f.folderId = :preid and f.fileName = :name")
    List<FileStore> findBynameAndFolderid(long preid,String name);
}
