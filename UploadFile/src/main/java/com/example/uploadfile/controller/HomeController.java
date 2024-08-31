package com.example.uploadfile.controller;

import com.example.uploadfile.dto.FolderStoreDto;
import com.example.uploadfile.models.FileStore;
import com.example.uploadfile.models.FolderStore;
import com.example.uploadfile.models.Item;
import com.example.uploadfile.service.FileService;
import com.example.uploadfile.service.FolderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class HomeController {

    private FileService fileService;
    private FolderService folderService;

    public HomeController(FileService fileService,FolderService folderService) {
        this.fileService = fileService;
        this.folderService = folderService;
    }

//    @GetMapping ("/")
//    public String home(Model model) {
//        List<FolderStore> listfolder = new ArrayList<>();
//        List<Item> fileStoreList = new ArrayList<>();
//        listfolder=folderService.getFolder();
//        model.addAttribute("files", fileStoreList);
//        model.addAttribute("folders", listfolder);
//        return "index";
//    }
    @GetMapping("/")
    public String showFolders(Model model) {
        List<FolderStore> allFolders = folderService.getFolder();
        List<FolderStoreDto> rootFolders = buildFolderTree(allFolders);
        model.addAttribute("folders", rootFolders);
        return "index";
    }

    private List<FolderStoreDto> buildFolderTree(List<FolderStore> allFolders) {
        Map<Long, FolderStoreDto> dtoMap = new HashMap<>();
        List<FolderStoreDto> rootFolders = new ArrayList<>();

        // Tạo DTO cho mỗi FolderStore
        for (FolderStore folder : allFolders) {
            FolderStoreDto dto = new FolderStoreDto(folder);
            dtoMap.put(folder.getFid(), dto);
        }

        // Xây dựng cấu trúc cây
        for (FolderStore folder : allFolders) {
            FolderStoreDto dto = dtoMap.get(folder.getFid());
            if (folder.getPreFolder() == 0L) {
                rootFolders.add(dto);
            } else {
                FolderStoreDto parentDto = dtoMap.get(folder.getPreFolder());
                if (parentDto != null) {
                    parentDto.getChildren().add(dto);
                }
            }
        }

        return rootFolders;
    }

    @GetMapping ("/go")
    public String go(@RequestParam("fid") long fid,Model model) {
        List<Item> fileStoreList = new ArrayList<>();
        fileStoreList=fileService.getFile(fid);
        List<FolderStore> allFolders = folderService.getFolder();
        List<FolderStoreDto> rootFolders = buildFolderTree(allFolders);
        model.addAttribute("folders", rootFolders);
        model.addAttribute("files", fileStoreList);
        return "index";
    }
    @GetMapping ("/getLink")
    public String getFileByFolder(@RequestParam("fid") List<Long> fid,Model model) {
        List<FolderStore> listfolder = new ArrayList<>();
        List<Item> fileStoreList = new ArrayList<>();
        TreeMap<Long, String> link = new TreeMap<>();
        listfolder = folderService.getFolderLink(fid.get(fid.size()-1));
        fileStoreList=fileService.getFile(fid.get(fid.size()-1) );
        for (FolderStore folder : listfolder) {
            link.put(folder.getFid(), folder.getFolderName());
        }
        List<FolderStore> allFolders = folderService.getFolder();
        List<FolderStoreDto> rootFolders = buildFolderTree(allFolders);
        model.addAttribute("folders", rootFolders);
        model.addAttribute("link", link);
        model.addAttribute("files", fileStoreList);
        model.addAttribute("curentFid", listfolder.get(0).getFid());
        return "index";
    }

    @GetMapping ("/back")
    public String back(@RequestParam("fidback") long fid,Model model) {
        try {
            List<Item> fileStoreList = new ArrayList<>();
            List<FolderStore> listfolder = new ArrayList<>();
            long preFid = folderService.getFilePre(fid);
            fileStoreList = fileService.getFile(preFid);
            List<FolderStore> allFolders = folderService.getFolder();
            List<FolderStoreDto> rootFolders = buildFolderTree(allFolders);
            TreeMap<Long, String> link = new TreeMap<>();
            listfolder = folderService.getFolderLink(preFid);
            for (FolderStore folder : listfolder) {
                link.put(folder.getFid(), folder.getFolderName());
            }
            model.addAttribute("folders", rootFolders);
            model.addAttribute("files", fileStoreList);
            model.addAttribute("link", link);
            model.addAttribute("curentFid", listfolder.get(0).getFid());
            return "index";
        }catch (Exception e){
            return "redirect:/";
        }
    }

    @PostMapping("/updateName")
    public String updateFileName(@RequestParam("fileId") long fileId, Model model, @RequestParam("newName") String newName) {
        FileStore optionalFile = fileService.getFileById(fileId);

        if (optionalFile != null) {
            optionalFile.setFileName(newName);
            optionalFile.setChangeDate(LocalDateTime.now());
            fileService.save(optionalFile);
        }
        List<FolderStore> listfolder = new ArrayList<>();
        List<Item> fileStoreList = new ArrayList<>();
        TreeMap<Long, String> link = new TreeMap<>();
        listfolder = folderService.getFolderLink(optionalFile.getFolderId());
        fileStoreList=fileService.getFile(optionalFile.getFolderId());
        for (FolderStore folder : listfolder) {
            link.put(folder.getFid(), folder.getFolderName());
        }
        List<FolderStore> allFolders = folderService.getFolder();
        List<FolderStoreDto> rootFolders = buildFolderTree(allFolders);
        model.addAttribute("folders", rootFolders);
        model.addAttribute("link", link);
        model.addAttribute("files", fileStoreList);
        model.addAttribute("curentFid", listfolder.get(0).getFid());
        return "index";
    }
}
