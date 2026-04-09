package com.ocean.problemsolvingjournal.systemdesign.filestorage.controller;

import com.ocean.problemsolvingjournal.systemdesign.filestorage.model.CompleteRequest;
import com.ocean.problemsolvingjournal.systemdesign.filestorage.model.FileDetails;
import com.ocean.problemsolvingjournal.systemdesign.filestorage.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/file/storage")
public class FileStorageController {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/multipart/pre-signed/url")
    public ResponseEntity<String> getPresignedUrl(@RequestParam("key") String key,
                                                  @RequestParam("uploadId") String uploadId,
                                                  @RequestParam("partNumber") int partNumber) {
        try {
            String result = fileStorageService.createPresidedUrl(key, uploadId, partNumber);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException | IllegalStateException e) {
            logger.error("Failed to create presigned url: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating presigned url", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/multipart/initiate")
    public ResponseEntity<Map<String, Object>> initiate(@RequestBody FileDetails fileDetails) {

        String folderName = fileDetails.getFolderName();
        String fileName = fileDetails.getFileName();
        long size = fileDetails.getSize();

        var meta = fileStorageService.initiate(folderName, fileName, size);

        return ResponseEntity.ok(Map.of("uploadId", meta.getUploadId(), "fileId", meta.getId(), "key", meta.getS3Key()));
    }

    @PostMapping("/multipart/complete")
    public ResponseEntity<String> complete(@RequestBody CompleteRequest req) {
        fileStorageService.complete(req);
        return ResponseEntity.ok("DONE");
    }
}
