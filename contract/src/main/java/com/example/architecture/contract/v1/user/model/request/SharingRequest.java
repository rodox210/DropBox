package com.example.architecture.contract.v1.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class SharingRequest {

    @NotBlank
    private String sourceId;
    @NotBlank
    private String fileName;
    @NotBlank
    private String recipientId;
}
