package com.example.architecture.impl.user.stubs;

import com.example.architecture.impl.user.model.UploadsUserModel;

public class UploadModelStub {

    public static UploadsUserModel generationUploadModel() {
        return UploadsUserModel.builder()
                .name("teste")
                .size(3)
                .timestamp(null)
                .build();
    }

    public static UploadsUserModel generationUploadModel2() {
        return UploadsUserModel.builder()
                .name("teste2")
                .size(1)
                .timestamp(null)
                .build();
    }

}

