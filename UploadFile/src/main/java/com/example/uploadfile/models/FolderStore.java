package com.example.uploadfile.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "folderstore")
public class FolderStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fid")
    private Long fid;

    @Column(name = "folder_name", nullable = false)
    private String folderName;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "pre_folder", nullable = false)
    private Long preFolder=0L;

    @OneToMany(mappedBy = "folderStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileStore> files;

}
