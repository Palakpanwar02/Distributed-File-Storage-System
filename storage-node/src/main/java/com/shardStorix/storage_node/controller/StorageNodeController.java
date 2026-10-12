package com.shardStorix.storage_node.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController//this controller will handle cmds from main application (to save chunks)
public class StorageNodeController {

    @PostMapping("/chunks")
    public String storageNode(@RequestParam("file")MultipartFile chunk) throws IOException
    {
        Path storagePath= Paths.get("MainStorageofChunks");//i want to work with this folder
        Files.createDirectories(storagePath);//if that folder doesnt exist just create it
        Path filePath = storagePath.resolve(chunk.getOriginalFilename());//creates the path to store chunks
        Files.write(filePath, chunk.getBytes());//take the bytes of this chunk and save it in the given location
        return "received chunk:" + chunk.getOriginalFilename();
    }

}
