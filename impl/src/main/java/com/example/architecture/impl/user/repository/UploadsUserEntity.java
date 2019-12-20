package com.example.architecture.impl.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Calendar;

@AllArgsConstructor
@lombok.Data
@Builder
public class UploadsUserEntity {
    private long size;
    private String name;
    private Calendar timestamp;

}
