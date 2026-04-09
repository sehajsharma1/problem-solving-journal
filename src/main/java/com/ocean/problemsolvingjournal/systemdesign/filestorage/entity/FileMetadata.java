package com.ocean.problemsolvingjournal.systemdesign.filestorage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "file_metadata", indexes = {
        @Index(columnList = "alias", unique = true)
})
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String s3Key;
    private String uploadId;
    private String status;
    private long fileSize;
    private LocalDateTime createdAt;
}
