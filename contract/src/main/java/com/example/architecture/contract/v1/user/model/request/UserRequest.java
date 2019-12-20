package com.example.architecture.contract.v1.user.model.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class UserRequest {

    @NotBlank
    @Size(min = 4, max = 20)
    private String name;

    @NotBlank
    @Email(message = "Invalid email")
    private String email;


}
