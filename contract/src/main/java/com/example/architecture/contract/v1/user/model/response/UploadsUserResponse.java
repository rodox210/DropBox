package com.example.architecture.contract.v1.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor

@lombok.Data
@Builder
public class UploadsUserResponse {

    private Calendar timestamp;
    private long size;
    private String name;
}
