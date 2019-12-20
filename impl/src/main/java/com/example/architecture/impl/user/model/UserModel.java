package com.example.architecture.impl.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@lombok.Data@Builder
public class UserModel {

    private String id;
    private String name;
    private String email;
}
