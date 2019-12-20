package com.example.architecture.impl.user;

import com.example.architecture.impl.user.mapper.SharingUserMapper;
import com.example.architecture.impl.user.mapper.UserMapper;
import com.example.architecture.impl.user.model.SharingModel;
import com.example.architecture.impl.user.model.UploadsUserModel;
import com.example.architecture.impl.user.model.UserModel;
import com.example.architecture.impl.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@AllArgsConstructor
public class UserFacade {

    private UserService service;

    public UserModel findById(String id) {
        return service.findById(id);
    }

    public void deleteById(String id)  {
        service.deleteAllSharedsUser(id);
        service.ftpDeleteFiilesById(id);
        service.deleteUserById(id);
    }

    public void deleteUploadUser(String id, String nameFile){
        service.validationFileName(id, nameFile);
        service.deleteUploadUser(id, nameFile);
    }

    public UserModel createUser(UserModel user) {
        UserModel saved = service.createUser(user);
        service.createFtpRepository(saved.getId());
        return saved;
    }

    public UserModel userUpdate(UserModel user, String id) {
        return UserMapper.mapToModel(service.userUpdate(user, id));
    }

    public void userUpload(String id, MultipartFile file) {
        service.userUpload(id, file);
    }

    public List<UserModel> allUsers() {
        return service.allUsers();
    }

    public List<UploadsUserModel> allUploadsUser(String id) {
        return service.allUploadsUser(id);
    }

    ////////////////////Comparts/////////////////
    public SharingModel createdSharedtFile(SharingModel compart) {
        service.validationFileName(compart.getSourceId(), compart.getFileName());
        return SharingUserMapper.mapToCompartModel(service.createdSharedtFile(compart));
    }


    public List<SharingModel> getSahredIdSource(String sourceId) {
        return service.getSahredIdSource(sourceId);
    }

    public List<SharingModel> getSahredfileName(String name) {
        return service.getSahredfileName(name);
    }

    public List<SharingModel> getSahredRecipientId(String id) {
        return service.getSahredRecipientId(id);
    }

    public void deleteSharedFindById(String id) {
        service.deleteSharedFindById(id);
    }
}
