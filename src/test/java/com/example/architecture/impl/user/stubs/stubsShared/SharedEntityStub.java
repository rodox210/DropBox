package com.example.architecture.impl.user.stubs.stubsShared;

import com.example.architecture.impl.user.repository.SharingEntity;

public class SharedEntityStub {
    public static SharingEntity generationSharedEntity() {
        return SharingEntity.builder()
                .id("genericId")
                .sourceId("teste")
                .fileName("teste@live.com")
                .recipientId("recepiente")
                .build();
    }

    public static SharingEntity generationSharedEntity2() {
        return SharingEntity.builder()
                .id("genericId2")
                .sourceId("teste2")
                .fileName("teste@live.com2")
                .recipientId("recepiente2")
                .build();
    }
}
