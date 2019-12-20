package com.example.architecture.impl.user.service;

import com.example.architecture.impl.exception.ApiException;
import com.example.architecture.impl.exception.ExceptionModel;
import com.example.architecture.impl.ftp.FtpConnection;
import com.example.architecture.impl.user.mapper.SharingUserMapper;
import com.example.architecture.impl.user.mapper.UploadsUserMapper;
import com.example.architecture.impl.user.mapper.UserMapper;
import com.example.architecture.impl.user.model.SharingModel;
import com.example.architecture.impl.user.model.UploadsUserModel;
import com.example.architecture.impl.user.model.UserModel;
import com.example.architecture.impl.user.repository.SharingEntity;
import com.example.architecture.impl.user.repository.UserEntity;
import com.example.architecture.impl.user.repository.interfaces.SharingRepository;
import com.example.architecture.impl.user.repository.interfaces.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {



    private UserRepository repository;
    private SharingRepository compartRepository;

    public UserModel findById(String id) {
        return UserMapper.mapToModel(repository.findById(id)
                .orElseThrow(this::getApiExceptionIdNotFound));
    }

    public List<UserModel> allUsers() {
        return repository.findAll().stream().map(UserMapper::mapToModel).collect(Collectors.toList());
    }

    public void ftpDeleteFiilesById(String id){
        try {
            FTPClient ftp = FtpConnection.connectionFtp();
            ftp.changeWorkingDirectory(id);
            Arrays.stream(ftp.listNames())
                    .forEach(name -> {
                        try {
                            ftp.deleteFile(name);
                        } catch (ApiException | IOException e) {
                            throw getApiExceptionConnection();
                        }
                    });

            ftp.changeWorkingDirectory("../");
            ftp.removeDirectory(id);
            FtpConnection.closeConnectionFtp();
        } catch (IOException exception) {
            throw getApiExceptionConnection();
        }

    }


    public void deleteUserById(String id) {
        if (repository.findById(id).isPresent()){
        repository.deleteById(id);}else{
            throw getApiExceptionIdNotFound();
        }
    }

    private ApiException getApiException(HttpStatus status, String name, String message) {
        return new ApiException(status, ExceptionModel.builder()
                .status(status)
                .name(name)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build());
    }

    public void deleteUploadUser(String id, String nameFile) {
        try {
                FTPClient ftp = FtpConnection.connectionFtp();
                ftp.changeWorkingDirectory(id);
                ftp.deleteFile(nameFile);
                FtpConnection.closeConnectionFtp();

        } catch (ApiException | NullPointerException | IOException exception) {
            throw getApiExceptionIdNotFound();
        }
    }



    public List<UploadsUserModel> allUploadsUser(String id) {
        if (repository.findById(id).isEmpty())
            throw getApiExceptionIdNotFound();

        try {
            List allUploadsId = new ArrayList();

            FTPClient ftp = FtpConnection.connectionFtp();
            ftp.changeWorkingDirectory(id);
            Arrays.stream(ftp.listFiles())
                    .forEach(name ->
                            allUploadsId.add(UploadsUserMapper.mapToModelUplodasUser(UploadsUserMapper.mapToFileUplodasUser(name)))
                    );
            FtpConnection.closeConnectionFtp();
            return allUploadsId;
        } catch (ApiException | IOException exception) {
            throw getApiException(HttpStatus.NOT_FOUND, "Incorrect target Id: " + id, "AllUploadsNotFoundException");
        }
    }

    public UserModel createUser(UserModel user) {
        if (repository.findByEmail(user.getEmail()).isPresent() && repository.findByName(user.getName()).isPresent()) {
            throw getApiException(HttpStatus.BAD_REQUEST, "Nome ou Email ja cadastrado", "RepeatFieldException");
        } else {
            return UserMapper.mapToModel(repository.save(UserMapper.mapToEntity(user)));
        }
    }

    public void createFtpRepository(String id) {
            try {
                FTPClient ftp = FtpConnection.connectionFtp();
                ftp.makeDirectory(id);
                FtpConnection.closeConnectionFtp();
            } catch (IOException exception) {
                throw getApiExceptionConnection();
            }
    }


    public UserEntity userUpdate(UserModel user, String id) {
        if (repository.findById(id).isPresent()){
        user.setId(id);
        repository.deleteById(id);
            return repository.save(UserMapper.mapToEntity(user));
        }
        else {
            throw getApiExceptionIdNotFound();
        }
    }

    public void userUpload(String id, MultipartFile file) {
        if (repository.findById(id).isEmpty()) {
            throw getApiExceptionIdNotFound();
        } else {
            try {
                FTPClient ftp = FtpConnection.connectionFtp();
                FtpConnection.targetingFtp(id);
                ftp.storeFile(id + file.getOriginalFilename(), file.getInputStream());
                FtpConnection.closeConnectionFtp();
            } catch (ApiException | IOException exception) {
                throw getApiExceptionConnection();
            }
        }
    }

    //////////////////Compartilhamento///////////////////////

   public SharingEntity createdSharedtFile(SharingModel compart) {
            if (repository.findById(compart.getSourceId()).isPresent()
                    && repository.findById(compart.getRecipientId()).isPresent()) {
                return compartRepository.save(SharingUserMapper.mapToCompartEntity(compart));
            } else {

                throw getApiException(HttpStatus.BAD_REQUEST, "Required field is null", "NullFieldException");
            }
    }


    public List<SharingModel> getSahredIdSource(String sourceId) {
        List<SharingEntity> shareEntities = compartRepository.findBySourceId(sourceId);
        if (repository.findById(sourceId).isEmpty())
            throw getApiExceptionIdNotFound();
        return shareEntities.stream().map(SharingUserMapper::mapToCompartModel).collect(Collectors.toList());
    }

    public List<SharingModel> getSahredfileName(String name) {
        List<SharingEntity> shareEntities = compartRepository.findByFileName(name);
        if (compartRepository.findByFileName(name).isEmpty())
            throw getApiException(HttpStatus.NOT_FOUND, "incorrect File Name: " + name, "FileNameNotFoundException");
        return shareEntities.stream().map(SharingUserMapper::mapToCompartModel).collect(Collectors.toList());
    }

    public List<SharingModel> getSahredRecipientId(String id) {
        List<SharingEntity> shareEntities = compartRepository.findByRecipientId(id);
        if (repository.findById(id).isEmpty())
            throw getApiExceptionIdNotFound();
        return shareEntities.stream().map(SharingUserMapper::mapToCompartModel).collect(Collectors.toList());
    }

    public void deleteSharedFindById(String id) {
        if (compartRepository.findDBById(id).isEmpty()) {
            throw getApiExceptionIdNotFound();
        }
        compartRepository.deleteById(id);
    }

    public void deleteAllSharedsUser(String id) {
        if (compartRepository.findDBById(id).isEmpty()) {
            throw getApiExceptionIdNotFound();
        }else{
        try{
        FTPClient ftp = FtpConnection.connectionFtp();
        ftp.changeWorkingDirectory(id);
        Arrays.stream(ftp.listNames())
                .forEach(name -> {


        for (SharingEntity listedFileName : compartRepository.findByFileName(name)) {
            compartRepository.delete(listedFileName);
        }}   );
                        FtpConnection.closeConnectionFtp();
                   } catch (IOException exception) {
                        throw getApiExceptionConnection();
                    }}
    }

    private ApiException getApiExceptionIdNotFound() {
        return new ApiException(HttpStatus.NOT_FOUND, ExceptionModel.builder()
                .message("TargetNotFoundException")
                .name("Id incorreto")
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @Generated
    private ApiException getApiExceptionConnection() {
        return new ApiException(HttpStatus.NOT_FOUND, ExceptionModel.builder()
                .message("Erro interno de conecxão")
                .name("ConnectionException")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build());
    }

    public Boolean validationFileName(String id, String fileName) {
        AtomicBoolean fileExists = new AtomicBoolean(false);
        try{
        if(repository.findById(id).isPresent()){
            FTPClient ftp = FtpConnection.connectionFtp();
            ftp.changeWorkingDirectory(id);
            Arrays.stream(ftp.listNames()).forEach(ftpFile -> {
                if (ftpFile.equals(fileName)) fileExists.set(true);
            });
            ftp.disconnect();
            if(!fileExists.get()) {
                throw  getApiException(HttpStatus.NOT_FOUND, "incorrect File Name: " + fileName, "FileNameNotFoundException");
            }
        }else{
           throw  getApiExceptionIdNotFound();
        }
        }catch (IOException e){
          throw  getApiExceptionConnection();
       }
        return fileExists.get();
    }


}

