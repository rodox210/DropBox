package com.example.architecture.impl.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)

public class GenericHandlerTest {

    @InjectMocks
    GenericHandler genericHandler;

    @Test
    public void handleApiException_ReturnHttpStatus_NOT_FOUND(){
        ApiException teste = new ApiException(HttpStatus.NOT_FOUND, ExceptionModel.builder().message("Id incorreto").name("TargetNotFoundException").status(HttpStatus.NOT_FOUND).timestamp(LocalDateTime.now()).build());

        genericHandler.handleApiException(teste);
        Assert.assertEquals(HttpStatus.NOT_FOUND, genericHandler.handleApiException(teste).getStatusCode());
    }

    @Test
    public void handleException_ReturnHttpStatus_INTERNAL_SERVER_ERROR(){
        Exception abc = new RuntimeException("teste");
        genericHandler.handleException(abc);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, genericHandler.handleException(abc).getStatusCode());
    }
}