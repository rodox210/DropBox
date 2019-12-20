package com.example.architecture.impl.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Calendar;

@AllArgsConstructor
@lombok.Data
@Builder
public class UploadsUserModel {
    private Calendar timestamp;
    private long size;
    private String name;
}
