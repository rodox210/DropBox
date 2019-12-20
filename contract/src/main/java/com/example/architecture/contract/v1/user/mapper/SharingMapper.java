package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.request.SharingRequest;
import com.example.architecture.contract.v1.user.model.response.SharingResponse;
import com.example.architecture.impl.user.model.SharingModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SharingMapper {

    public static SharingResponse mapToContractCompart(SharingModel sharingModel) {
        return SharingResponse.builder()
                .id(sharingModel.getId())
                .sourceId(sharingModel.getSourceId())
                .fileName(sharingModel.getFileName())
                .recipientId(sharingModel.getRecipientId()).build();
    }

    public static SharingModel mapToImplCompart(SharingRequest sharingRequest) {
        return SharingModel.builder()
                .sourceId(sharingRequest.getSourceId())
                .fileName(sharingRequest.getFileName())
                .recipientId(sharingRequest.getRecipientId()).build();
    }
}
