package com.ocean.problemsolvingjournal.systemdesign.filestorage.service;

import com.ocean.problemsolvingjournal.systemdesign.filestorage.entity.FileMetadata;
import com.ocean.problemsolvingjournal.systemdesign.filestorage.model.CompleteRequest;
import com.ocean.problemsolvingjournal.systemdesign.filestorage.repository.FileMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final S3Presigner presigner;
    private final S3Client s3Client;
    private final FileMetadataRepository repo;

    @Value("${aws.s3.bucket:}")
    private String bucket;

    public FileStorageService(S3Presigner presigner, S3Client s3Client, FileMetadataRepository repo) {
        this.presigner = presigner;
        this.s3Client = s3Client;
        this.repo = repo;
    }

    public String createPresidedUrl(String key, String uploadId, int partNumber) {
        if (bucket == null || bucket.isEmpty()) {
            throw new IllegalStateException("S3 bucket is not configured (aws.s3.bucket)");
        }
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("keyName must not be null or empty");
        }

        var request = UploadPartRequest.builder()
                .bucket(bucket)
                .key(key)
                .uploadId(uploadId)
                .partNumber(partNumber)
                .build();

        var presign = presigner.presignUploadPart(
                UploadPartPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(10))
                        .uploadPartRequest(request)
                        .build()
        );
        logger.info("Presigned URL to upload a file to: [{}]", presign.url());
        return presign.url().toString();
    }

    public FileMetadata initiate(String folderName, String fileName, long size) {

        String key = folderName + "/" + fileName;

        var response = s3Client.createMultipartUpload(
                CreateMultipartUploadRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build()
        );

        FileMetadata meta = FileMetadata.builder()
                .fileName(fileName)
                .s3Key(key)
                .uploadId(response.uploadId())
                .status("IN_PROGRESS")
                .fileSize(size)
                .createdAt(LocalDateTime.now())
                .build();

        return repo.save(meta);
    }

    public void complete(CompleteRequest req) {

        List<CompletedPart> parts = req.getParts().stream()
                .map(p -> CompletedPart.builder()
                        .partNumber(p.getPartNumber())
                        .eTag(p.getEtag())
                        .build())
                .toList();

        var upload = CompletedMultipartUpload.builder()
                .parts(parts)
                .build();

        s3Client.completeMultipartUpload(
                CompleteMultipartUploadRequest.builder()
                        .bucket(bucket)
                        .key(req.getKey())
                        .uploadId(req.getUploadId())
                        .multipartUpload(upload)
                        .build()
        );

        FileMetadata meta = repo.findById(req.getFileId()).orElseThrow();
        meta.setStatus("COMPLETED");
        repo.save(meta);
    }

}
