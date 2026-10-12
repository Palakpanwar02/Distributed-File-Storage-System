package com.shardStorix.majorpro.controller;

import com.shardStorix.majorpro.Service.FileMetaDataService;
import com.shardStorix.majorpro.client.StorageNodeClient;
import com.shardStorix.majorpro.entity.FileMetaData;
import com.shardStorix.majorpro.entity.StorageNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileMetaDataController {
    private final FileMetaDataService service;
   // private final StorageNodeClient storageNodeClient;
    public FileMetaDataController(FileMetaDataService service, StorageNodeClient storageNodeClient){
        this.service=service;//dependency injection of service
    //    this.storageNodeClient=storageNodeClient;//another dependency injection
    }

    @PostMapping
    public FileMetaData createFileMetaData(@RequestParam("file") MultipartFile uploadedFile) throws IOException {
       // storageNodeClient.sendChunk(
         //       uploadedFile.getBytes(),
           //     uploadedFile.getOriginalFilename()
       // );
         return service.saveFileMetaData(
                 uploadedFile.getBytes(),//converts the entire file into bytes not chunks
                 uploadedFile.getOriginalFilename(),
                 uploadedFile.getSize()
         );//will send the information to service and then service will put it into entity object****
    }
}
