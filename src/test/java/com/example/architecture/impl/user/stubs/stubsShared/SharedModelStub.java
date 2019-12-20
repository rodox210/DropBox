package com.example.architecture.impl.user.stubs.stubsShared;

import com.example.architecture.impl.user.model.SharingModel;

public class SharedModelStub {
    public static SharingModel generationSharedModel() {
        return SharingModel.builder()
                .id("genericId")
                .sourceId("teste")
                .fileName("teste@live.com")
                .recipientId("recepiente")
                .build();
    }

    public static SharingModel generationSharedModel2() {
        return SharingModel.builder()
                .id("genericId2")
                .sourceId("teste2")
                .fileName("teste@live.com2")
                .recipientId("recepiente2")
                .build();
    }
}
