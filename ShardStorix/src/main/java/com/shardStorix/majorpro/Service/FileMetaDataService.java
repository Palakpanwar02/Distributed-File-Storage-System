package com.shardStorix.majorpro.Service;

import com.shardStorix.majorpro.client.StorageNodeClient;
import com.shardStorix.majorpro.entity.FileMetaData;
import com.shardStorix.majorpro.repository.FileMetaDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//service has two things a dependency injection so that it can accumulate all the functions of repo like save(),find() etc
//next is it sets the data in entity's object ,first entity will create only variables and class ,gives a defined structure but
//not the actual data the actual data will come from service by using setters
@Service
public class FileMetaDataService {
    private final FileMetaDataRepository repository;//this one i created
    private final ChunkService chunkService;//another injection
    private final StorageNodeClient storageNodeClient;

    public FileMetaDataService(FileMetaDataRepository repository,ChunkService chunkService,StorageNodeClient storageNodeClient ){//this guy came from the backend of repo

        this.repository=repository;//Dependency injection
        this.chunkService=chunkService;
        this.storageNodeClient=storageNodeClient;
    }

    public FileMetaData saveFileMetaData(byte[] fileBytes,String fileName,long fileSize){
        List<byte[]> chunks = chunkService.splitIntoChunks(fileBytes);
        int chunkNumber = 1;

        for (byte[] ch : chunks) {//naming of chunks
            String chunkName = fileName + "_chunk_" + chunkNumber;
            storageNodeClient.sendChunk(ch, chunkName);
            chunkNumber++;//connection between chunkService and storagenodeclient
        }
        FileMetaData file=new FileMetaData();
        file.setFileName(fileName);
        file.setFileSize(fileSize);

        return repository.save(file);
    }
}
