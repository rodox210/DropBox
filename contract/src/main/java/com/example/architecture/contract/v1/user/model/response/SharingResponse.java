package com.example.architecture.contract.v1.user.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data
@Builder
public class SharingResponse {
    private String id;
    private String sourceId;
    private String fileName;
    private String recipientId;
}
