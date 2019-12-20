package com.example.architecture.contract.v1.user.mapper;

import com.example.architecture.contract.v1.user.model.response.UploadsUserResponse;
import com.example.architecture.impl.user.model.UploadsUserModel;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadUserMapper {
    public static UploadsUserResponse mapToContractUploads(UploadsUserModel uploadsModel) {
        return UploadsUserResponse.builder()
                .timestamp(uploadsModel.getTimestamp())
                .size(uploadsModel.getSize())
                .name(uploadsModel.getName()).build();
    }
}
