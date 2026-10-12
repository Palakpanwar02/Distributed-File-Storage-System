package com.shardStorix.majorpro.repository;

import com.shardStorix.majorpro.entity.StorageNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StorageNodeRepo extends JpaRepository<StorageNode, UUID> {
}
