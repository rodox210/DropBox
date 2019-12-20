package com.example.architecture.impl.user.stubs;

import com.example.architecture.impl.user.model.UserModel;

public class UserModelStub {

    public static UserModel generationUserModel() {
        return UserModel.builder()
                .id("genericId")
                .name("teste")
                .email("teste@live.com")
                .build();
    }

    public static UserModel generationUserModel2() {
        return UserModel.builder()
                .id("genericId2")
                .name("Chapolin")
                .email("chapolin@live.com")
                .build();
    }
}
