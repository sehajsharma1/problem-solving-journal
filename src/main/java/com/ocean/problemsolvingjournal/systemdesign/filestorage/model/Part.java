package com.ocean.problemsolvingjournal.systemdesign.filestorage.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Part {
    private int partNumber;
    private String etag;
}