package com.example.architecture.contract.v1.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
}
