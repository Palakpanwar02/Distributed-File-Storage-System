package com.shardStorix.majorpro.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//1 chunk=1 MB
@Service
public class ChunkService {
    private static final int CHUNK_SIZE = 1024 * 1024;//1,048,576 bytes = 1 MB

    public List<byte[]> splitIntoChunks(byte[] fileBytes) {
        List<byte[]> chunks = new ArrayList<>();
        for (int start = 0; start < fileBytes.length; start += CHUNK_SIZE) {
            int end = Math.min(start + CHUNK_SIZE, fileBytes.length);
            byte[] chunk = Arrays.copyOfRange(fileBytes, start, end);
            chunks.add(chunk);
        }

        return chunks;
    }
}
