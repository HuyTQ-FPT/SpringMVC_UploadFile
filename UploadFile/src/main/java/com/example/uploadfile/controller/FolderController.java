package com.example.uploadfile.controller;

import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.service.FileService;
import com.example.uploadfile.service.FolderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class FolderController {

    private FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping("/createFolder")
    public String createFolder(@RequestParam("folderId") long folderId, @RequestParam("newNameFolder") String folderName , Model model) {
        try {
            FolderStore folderStore = new FolderStore();
            folderStore.setFolderName(folderName);
            folderStore.setPreFolder(folderId);
            folderStore.setCreateDate(LocalDateTime.now());
            folderService.save(folderStore);
        } catch (Exception e) {
            model.addAttribute("message", "folder create failed: " + e.getMessage());
        }
        return "redirect:/getLink?fid=" + folderId;
    }
}
