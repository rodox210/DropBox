package com.example.architecture.impl.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class SharingEntity {

    private String id;
    private String sourceId;
    private String fileName;
    private String recipientId;
}
