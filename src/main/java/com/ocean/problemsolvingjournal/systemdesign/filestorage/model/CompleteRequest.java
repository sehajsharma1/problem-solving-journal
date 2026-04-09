package com.ocean.problemsolvingjournal.systemdesign.filestorage.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CompleteRequest {
    private Long fileId;
    private String uploadId;
    private String key;
    private List<Part> parts;
}
