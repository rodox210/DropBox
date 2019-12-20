package com.example.architecture.impl.user.mapper;

import com.example.architecture.impl.user.model.SharingModel;
import com.example.architecture.impl.user.repository.SharingEntity;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SharingUserMapper {

    public static SharingModel mapToCompartModel(SharingEntity userCompart) {
        return SharingModel.builder()
                .id(userCompart.getId())
                .sourceId(userCompart.getSourceId())
                .fileName(userCompart.getFileName())
                .recipientId(userCompart.getRecipientId())
                .build();
    }

    public static SharingEntity mapToCompartEntity(SharingModel sharingModel) {
        return SharingEntity.builder()
                .id(sharingModel.getId())
                .sourceId(sharingModel.getSourceId())
                .fileName(sharingModel.getFileName())
                .recipientId(sharingModel.getRecipientId())
                .build();
    }
}
