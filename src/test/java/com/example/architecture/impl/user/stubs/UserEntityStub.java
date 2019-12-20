package com.example.architecture.impl.user.stubs;

import com.example.architecture.impl.user.repository.UserEntity;

public class UserEntityStub {

    public static UserEntity generationUserEntity() {
        return UserEntity.builder()
                .id("someid")
                .name("teste")
                .email("teste@live.com")
                .build();
    }

    public static UserEntity generationUserEntity2() {
        return UserEntity.builder()
                .id("genericId2")
                .name("Chapolin")
                .email("chapolin@live.com")
                .build();
    }

}
