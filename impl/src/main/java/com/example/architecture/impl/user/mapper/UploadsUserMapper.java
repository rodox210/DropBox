package com.example.architecture.impl.user.mapper;

import com.example.architecture.impl.user.model.UploadsUserModel;
import com.example.architecture.impl.user.repository.UploadsUserEntity;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.commons.net.ftp.FTPFile;
@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadsUserMapper {

    public static UploadsUserEntity mapToFileUplodasUser(FTPFile uploadsUserEntity) {
        return UploadsUserEntity.builder()
                .name(uploadsUserEntity.getName())
                .size(uploadsUserEntity.getSize())
                .timestamp(uploadsUserEntity.getTimestamp())
                .build();
    }

    public static UploadsUserModel mapToModelUplodasUser(UploadsUserEntity uploadsUserModel) {
        return UploadsUserModel.builder()
                .name(uploadsUserModel.getName())
                .size(uploadsUserModel.getSize())
                .timestamp(uploadsUserModel.getTimestamp())
                .build();
    }
}
