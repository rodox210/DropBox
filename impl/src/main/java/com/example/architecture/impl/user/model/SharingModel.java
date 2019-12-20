package com.example.architecture.impl.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data
@Builder
public class SharingModel {
    private String id;
    private String sourceId;
    private String fileName;
    private String recipientId;
}
