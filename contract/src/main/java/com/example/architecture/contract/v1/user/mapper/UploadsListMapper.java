package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.response.UploadsListResponse;
import com.example.architecture.contract.v1.user.model.response.UploadsUserResponse;
import com.example.architecture.impl.user.model.UploadsUserModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class UploadsListMapper {

    public static UploadsListResponse mapUploadsListToResponse(List<UploadsUserModel> uploadsList) {
        List<UploadsUserResponse> collect = uploadsList.stream().map(uploadsUserModel -> UploadsUserResponse.builder()
                .timestamp(uploadsUserModel.getTimestamp())
                .size(uploadsUserModel.getSize())
                .name(uploadsUserModel.getName())
                .build()).collect(Collectors.toList());
        return UploadsListResponse.builder()
                .uploadsUserResponseList(collect)
                .size(collect.size())
                .build();
    }
}
