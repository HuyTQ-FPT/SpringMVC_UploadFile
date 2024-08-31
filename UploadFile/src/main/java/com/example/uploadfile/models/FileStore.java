package com.example.uploadfile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "filestore")
public class FileStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_data")
    private byte[] fileData;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @Column(name = "folderid")
    private Long folderId=1L;

    @ManyToOne
    @JoinColumn(name = "fid")
    private FolderStore folderStore;
}
