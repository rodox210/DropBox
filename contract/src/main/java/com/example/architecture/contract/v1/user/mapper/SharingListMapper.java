package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.response.SharingResponse;
import com.example.architecture.contract.v1.user.model.response.SharingListResponse;
import com.example.architecture.impl.user.model.SharingModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class SharingListMapper {
    public static SharingListResponse mapSharingListToResponse(List<SharingModel> sharingList) {
        List<SharingResponse> collect = sharingList.stream().map(compartModel -> SharingResponse.builder()
                .id(compartModel.getId())
                .sourceId(compartModel.getSourceId())
                .fileName(compartModel.getFileName())
                .recipientId(compartModel.getRecipientId())
                .build()).collect(Collectors.toList());
        return SharingListResponse.builder()
                .sharingListResponse(collect)
                .size(collect.size())
                .build();
    }
}
