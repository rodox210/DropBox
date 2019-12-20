package com.example.architecture.impl.user.stubs;

import com.example.architecture.impl.user.repository.UploadsUserEntity;

public class UploadEntityStub {

    ;

    public static UploadsUserEntity generationUploadFile() {
        return UploadsUserEntity.builder()
                .name("teste")
                .size(3)
                .timestamp(null)
                .build();
    }

    public static UploadsUserEntity generationUploadEntity2() {
        return UploadsUserEntity.builder()
                .name("teste2")
                .size(1)
                .timestamp(null)
                .build();
    }

    public static UploadsUserEntity generationUploadEntity() {
        return UploadsUserEntity.builder()
                .name("teste")
                .size(3)
                .timestamp(null)
                .build();
    }

}


