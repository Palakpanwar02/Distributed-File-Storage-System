package com.shardStorix.majorpro.client;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

//this class will communicate with the other application that is storage-node ,it will send HTTP request so that we can transfer chunks to other app
@Component
public class StorageNodeClient {
    private final RestClient restClient;//RestClient is a tool provided by springboot which enables communication with other app over HTTP

    public StorageNodeClient() {
        this.restClient = RestClient.create("http://localhost:8081");//base URL of the storage-node apps port that has been set to 8081
    }

    public String sendChunk(byte[] fileBytes, String fileName) {

        ByteArrayResource resource = new ByteArrayResource(fileBytes) {//it will wrap the chunks so that they can be treated as a file not exactlt a file by spring boot
            @Override
            public String getFilename() {
                return fileName;
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();//building form-data
        body.add("file", resource);

        return restClient.post()
                .uri("/chunks")
                .contentType(MediaType.MULTIPART_FORM_DATA)//telling the server that we are sending form data
                .body(body)
                .retrieve()
                .body(String.class);
    }
}
