/*
package com.example.architecture.impl.user.service;

import com.example.architecture.impl.exception.ApiException;
import com.example.architecture.impl.user.model.UploadsUserModel;
import com.example.architecture.impl.user.model.UserModel;
import com.example.architecture.impl.user.repository.UserEntity;
import com.example.architecture.impl.user.repository.interfaces.SharingRepository;
import com.example.architecture.impl.user.repository.interfaces.UserRepository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();



    @Mock
    UserRepository userRepository;

    @Mock
    SharingRepository sharingRepository;

    @InjectMocks
    UserService userService;


    @Test
    public void getCompartsIdSource_ReturnApiException() {
        expectedException.expect(ApiException.class);
        expectedException.expectMessage("TargetNotFoundException");
        when(sharingRepository.findBySourceId(anyString())).thenReturn(Collections.emptyList());
        userService.getSahredIdSource("teste");
    }



    @Test(expected = ApiException.class)
    public void getSahredfileName_ReturnApiException() {
        when(sharingRepository.findByFileName(anyString())).thenReturn(Collections.emptyList());
        userService.getSahredfileName("teste");
    }

    @Test(expected = ApiException.class)
    public void getSahredRecipientId_ReturnApiException() {
        when(sharingRepository.findByRecipientId(anyString())).thenReturn(Collections.emptyList());
        userService.getSahredRecipientId("teste");
    }


    @Test(expected = ApiException.class)
    public void deleteSharedFindById_ReturnApiException() {
        when(sharingRepository.findDBById(any())).thenReturn(Optional.empty());
        userService.deleteSharedFindById("someid");
    }

    @Test(expected = ApiException.class)
    public void deleteAllCompartsUser_ReturnApiException() {
        when(sharingRepository.findDBById(any())).thenReturn(Optional.empty());
        userService.deleteAllSharedsUser("someid");
    }


    @Test(expected = ApiException.class)
    public void userUpload_ReturnApiException() {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        userService.userUpload("teste", multipartFile);
    }


    @Test(expected = ApiException.class)
    public void allUploadsUser_ReturnApiException() {
        when(userRepository.findById("someid")).thenReturn(Optional.empty());
        List<UploadsUserModel> teste = userService.allUploadsUser("someid");
    }

    @Test(expected = ApiException.class)
    public void create_ReturnApiException() {
        UserModel userModel = new UserModel("someid","teste","teste@hotmail.com");
        UserEntity userEntity = new UserEntity("someid","teste","teste@hotmail.com");
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(userEntity));
        when(userRepository.findByName(any())).thenReturn(Optional.of(userEntity));
        userService.createUser(userModel);
    }


    @Test(expected = ApiException.class)
    public void validationFileNameReturnApiException() {
        UserEntity userEntity = new UserEntity("someid","teste","teste@hotmail.com");
        when(userRepository.findById("someid")).thenReturn(Optional.of(userEntity));
        userService.validationFileName("someid", "fileName");
    }

    @Test(expected = ApiException.class)
    public void validationFileName2_ReturnApiException() {
        userService.validationFileName("", "fileName");
    }


}
*/