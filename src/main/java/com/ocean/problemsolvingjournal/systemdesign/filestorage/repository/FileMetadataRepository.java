package com.ocean.problemsolvingjournal.systemdesign.filestorage.repository;

import com.ocean.problemsolvingjournal.systemdesign.filestorage.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {

}
