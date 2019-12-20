/*package com.example.architecture.contract.v1.user;

import com.example.architecture.impl.user.mapper.UserMapper;
import com.example.architecture.impl.user.model.SharingModel;
import com.example.architecture.impl.user.model.UserModel;
import com.example.architecture.impl.user.repository.SharingEntity;
import com.example.architecture.impl.user.repository.UserEntity;
import com.example.architecture.impl.user.repository.interfaces.SharingRepository;
import com.example.architecture.impl.user.repository.interfaces.UserRepository;
import com.example.architecture.impl.user.stubs.UserEntityStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SharingRepository sharingRepository;


    @Test
    public void findById_ReturnCode_OK() throws Exception {
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(UserEntityStub.generationUserEntity()));
       this.mockMvc.perform(get("/v1/dropbox/user/someid"))
               .andExpect(status().isOk()
               ).andExpect(jsonPath("$.id").value("someid"));
    }


    @Test
    public void allUsers_ReturnCode_OK() throws Exception {

        given(userRepository.findAll()).willReturn(Collections.emptyList());
        this.mockMvc.perform(get("/v1/dropbox/user")).andExpect(status().isOk());
    }

    @Test
    public void allUploadsUser_ReturnCode_Ok() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(get("/v1/dropbox/user/uploads/someid")).andExpect(status().isOk());
    }

    @Test
    public void createUser_ReturnCode_Created() throws Exception {
        given(userRepository.save(UserEntity.builder().name("jonas").email("jacare@live.com")
                .build())).willReturn(UserEntity
                        .builder()
                        .id("someid")
                        .name("teste")
                        .email("teste@hotmail.com")
                        .build());

        this.mockMvc.perform(post("/v1/dropbox/user").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(UserEntity
                                .builder()
                                .name("jonas")
                                .email("jacare@live.com")
                                .build()))).andExpect(status().isCreated());
    }

    @Test
    public void deleteFindById_ReturnCode_Ok() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        SharingEntity sharingEntityExample = new SharingEntity("xxxx","xxxxx","xxxx","xxxx");

        given(sharingRepository.findDBById("someid")).willReturn(java.util.Optional.of(sharingEntityExample));
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(delete("/v1/dropbox/user/someid")).andExpect(status().isOk());
    }

    @Test
    public void deleteUploadUser_ReturnCode_Ok() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(multipart("/v1/dropbox/user/upload/someid").file(multipartFile));
        this.mockMvc.perform(delete("/v1/dropbox/user/upload/someid?nameFile=someidtest.txt")).andExpect(status().isOk());
    }

    @Test
    public void updateUser_ReturnCode_OK() throws Exception {
        UserModel userModelExample = new UserModel("someid","teste","test@hotmail.com");
        UserEntity userEntityExample = new UserEntity("someid","teste","test@hotmail.com");
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        given(userRepository.save(UserMapper.mapToEntity(userModelExample))).willReturn(userEntityExample);

        this.mockMvc.perform(put("/v1/dropbox/user/someid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper()
                        .writeValueAsString(UserEntity.builder().name("teste").email("test@hotmail.com").build()))).andExpect(status().isOk());
    }

    @Test
    public void userUpload_ReturnCode_OK() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(multipart("/v1/dropbox/user/upload/someid").file(multipartFile)).andExpect(status().isOk());
    }

   @Test
    public void creatUserCompartFile_ReturnCode_Created() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        SharingEntity sharingModelExample = new SharingEntity("someid","someid","someidtest.txt","someid");
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        given(sharingRepository.save(any())).willReturn(sharingModelExample);

       this.mockMvc.perform(multipart("/v1/dropbox/user/upload/someid").file(multipartFile));

       this.mockMvc.perform(post("/v1/dropbox/share")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(SharingModel
                        .builder()
                        .id("someid")
                        .sourceId("someid")
                        .fileName("someidtest.txt")
                        .recipientId("someid")
                        .build()))).andExpect(status().isCreated());

    }

    @Test
    public void getSahredIdSource_ReturnCode_OK() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        given(sharingRepository.findBySourceId("someid")).willReturn(Collections.emptyList());
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(get("/v1/dropbox/share/source/someid")).andExpect(status().isOk());
    }

    @Test
    public void getSahredfileName_ReturnCode_OK() throws Exception {
        SharingEntity sharingEntityExample = new SharingEntity("someid","someid","fileName","someid");
        List<SharingEntity> testList= new ArrayList<>();
        testList.add(sharingEntityExample);
        given(sharingRepository.findByFileName("fileName")).willReturn(testList);
        this.mockMvc.perform(get("/v1/dropbox/share/shareName/fileName")).andExpect(status().isOk());
    }

    @Test
    public void getSahredRecipientId_ReturnCode_OK() throws Exception {
        UserEntity userEntityExample = new UserEntity("someid","teste","test@mail.com");
        given(sharingRepository.findByRecipientId("someid")).willReturn(Collections.emptyList());
        given(userRepository.findById("someid")).willReturn(java.util.Optional.of(userEntityExample));
        this.mockMvc.perform(get("/v1/dropbox/share/recipient/someid")).andExpect(status().isOk());
    }

    @Test
    public void deleteSharedFindById_ReturnCode_NoContent() throws Exception {
        SharingEntity sharingEntityExample = new SharingEntity("xxxx","xxxxx","xxxx","xxxx");
        given(sharingRepository.findDBById("someid")).willReturn(java.util.Optional.of(sharingEntityExample));
        this.mockMvc.perform(delete("/v1/dropbox/share/someid")).andExpect(status().isNoContent());
    }
}
*/




