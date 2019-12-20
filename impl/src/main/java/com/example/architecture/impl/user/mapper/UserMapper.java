package com.example.architecture.impl.user.mapper;

import com.example.architecture.impl.user.model.UserModel;
import com.example.architecture.impl.user.repository.UserEntity;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {


    public static UserModel mapToModel(UserEntity userEntity) {
        return UserModel.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }

    public static UserEntity mapToEntity(UserModel userModel) {
        return UserEntity.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .build();
    }
}


