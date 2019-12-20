package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.response.UserListResponse;
import com.example.architecture.contract.v1.user.model.response.UserResponse;
import com.example.architecture.impl.user.model.UserModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UserListMapper {
    public static UserListResponse mapUserListToResponse(List<UserModel> userList) {
        List<UserResponse> collect = userList.stream().map(userModel -> UserResponse.builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .build()).collect(Collectors.toList());
        return UserListResponse.builder()
                .userResponseList(collect)
                .size(collect.size())
                .build();
    }

}
