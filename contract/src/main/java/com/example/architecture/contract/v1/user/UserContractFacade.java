package com.example.architecture.contract.v1.user;

import com.example.architecture.contract.v1.user.mapper.*;
import com.example.architecture.contract.v1.user.model.request.SharingRequest;
import com.example.architecture.contract.v1.user.model.response.*;
import com.example.architecture.contract.v1.user.model.request.UserRequest;
import com.example.architecture.impl.user.UserFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Slf4j
@Component
public class UserContractFacade {
    private UserFacade facade;


    UserResponse findById(String id) {
        return UserMapper.mapToContract(facade.findById(id));
    }

    void deleteById(String id) {
        facade.deleteById(id);
    }

    void deleteUploadUser(String id, String nameFile) {
        facade.deleteUploadUser(id, nameFile);
    }

    UserResponse createUser(UserRequest user) {
        return UserMapper.mapToContract(facade.createUser(UserMapper.mapToImpl(user)));
    }

    UserResponse userUpdate(UserRequest user, String id) {
        return UserMapper.mapToContract(facade.userUpdate(UserMapper.mapToImpl(user), id));
    }

    void userUpload(String id, MultipartFile file)  {
        facade.userUpload(id, file);
    }

    UserListResponse allUsers() {

        return UserListMapper.mapUserListToResponse(facade.allUsers());
    }

    UploadsListResponse allUploadsUser(String id) {
        return UploadsListMapper.mapUploadsListToResponse(facade.allUploadsUser(id));
    }


    SharingResponse createdSharedtFile(SharingRequest compart) {
        return SharingMapper.mapToContractCompart(facade.createdSharedtFile(SharingMapper.mapToImplCompart(compart)));
    }

    SharingListResponse getSahredIdSource(String sourceId) {

        return SharingListMapper.mapSharingListToResponse(facade.getSahredIdSource(sourceId));

    }

    SharingListResponse getSahredfileName(String name) {

        return SharingListMapper.mapSharingListToResponse(facade.getSahredfileName(name));
    }

    SharingListResponse getSahredRecipientId(String id) {

        return SharingListMapper.mapSharingListToResponse(facade.getSahredRecipientId(id));
    }

    void deleteSharedFindById(String id) {
        facade.deleteSharedFindById(id);
    }

}
