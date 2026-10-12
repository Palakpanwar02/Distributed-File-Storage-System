package com.shardStorix.majorpro.repository;

import com.shardStorix.majorpro.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileMetaDataRepository extends JpaRepository<FileMetaData , UUID> {

}
