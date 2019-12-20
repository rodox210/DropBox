package com.example.architecture.impl.user.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data
@Builder
public class UserEntity {

    private String id;
    private String name;
    private String email;
}
