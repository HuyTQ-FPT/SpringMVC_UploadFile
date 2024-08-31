package com.example.uploadfile.controller;

import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.service.FileService;
import com.example.uploadfile.service.FolderService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class FileController {

    private FileService fileService;
    private FolderService folderService;

    public FileController(FileService fileService, FolderService folderService) {
        this.fileService = fileService;
        this.folderService = folderService;
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files,
                                   @RequestParam("preid") long preid,
                                   @RequestParam("typeUpload") String type,
                                   Model model) {
        try {
            System.out.println("typeeeeeeeeeeeeeeeeeee" + type);
            System.out.println("preidfddddddddddddddddddddd" + preid);
            List<FileStore> fileStoreList = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                byte[] fileData = file.getBytes();
                String[] idArray = fileName.split("/");
                if (idArray.length > 0) {
                    if (!folderService.checkFolderExist(idArray[0], preid)) {
                        FolderStore folderStore = new FolderStore();
                        folderStore.setFolderName(idArray[0]);
                        folderStore.setPreFolder(preid);
                        folderStore.setCreateDate(LocalDateTime.now());
                        folderService.save(folderStore);

                    }
                }

                FolderStore newFolderUpdate = new FolderStore();
                newFolderUpdate = folderService.getByNameAndPreId(idArray[0], preid);

                if (fileService.checkFileUploadExist(newFolderUpdate.getFid(), fileName)) {
                    FileStore uploadedFile = fileService.getFileUploadExist(newFolderUpdate.getFid(), fileName);
                    uploadedFile.setFileData(fileData);
                    fileService.save(uploadedFile);
                } else {
                    FileStore uploadedFile = new FileStore();
                    uploadedFile.setFileName(fileName);
                    uploadedFile.setFileData(fileData);
                    uploadedFile.setCreateDate(LocalDateTime.now());
                    uploadedFile.setFolderId(newFolderUpdate.getFid());
                    fileStoreList.add(uploadedFile);
                }
            }
            fileService.listSave(fileStoreList);
            model.addAttribute("message", "File uploaded successfully: " + files.length);
        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }
        return "redirect:/getLink?fid=" + preid;
    }

    @GetMapping("/downloadfile")
    public String downloadFile(@RequestParam("ids") String ids, Model model, HttpServletResponse response) throws IOException {
        String[] idArray = ids.split(",");
        if (idArray.length == 0) {
            return "index";
        }
        if (idArray.length == 1) {
            Integer id = Integer.parseInt(idArray[0]);
            FileStore temp = fileService.getFileById(id);
            if (temp != null) {
                response.setContentType("application/octet-stream");
                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename = " + temp.getFileName();
                response.setHeader(headerKey, headerValue);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(temp.getFileData());
                outputStream.close();
            }
        } else {
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=downloaded_files.zip");

            try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
                for (String idStr : idArray) {
                    Integer id = Integer.parseInt(idStr);
                    FileStore file = fileService.getFileById(id);
                    if (file != null) {
                        ZipEntry zipEntry = new ZipEntry(file.getFileName());
                        zipOut.putNextEntry(zipEntry);
                        zipOut.write(file.getFileData());
                        zipOut.closeEntry();
                    }
                }
            }
        }
        return "stay";
    }

    @PostMapping("/deletefile")
    public String deleteFile(@RequestParam("ids") String ids,
                             @RequestParam("type") String type,
                             @RequestParam("preid1") long preid1,
                             Model model, HttpServletResponse response) throws IOException {
        String[] idArray = ids.split(",");
        if (idArray.length == 0) {
            return "index";
        }
        if (idArray.length == 1) {
            if (type.equals("File")) {
                Long id = Long.parseLong(idArray[0]);
                fileService.deleteFile(id);
            } else {
                folderService.deleteFolder(Long.parseLong(idArray[0]));
            }

        } else {
            for (String idStr : idArray) {
                if (type.equals("File")) {
                    Long id = Long.parseLong(idStr);
                    fileService.deleteFile(id);
                } else {
                    folderService.deleteFolder(Long.parseLong(idStr));
                }

            }
        }
        return "redirect:/getLink?fid=" + preid1;
    }

}
