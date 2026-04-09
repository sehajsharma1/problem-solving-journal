package com.ocean.problemsolvingjournal.systemdesign.filestorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDetails {

    private String fileName;
    private String folderName;
    private long size;
}
