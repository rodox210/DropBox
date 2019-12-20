package com.example.architecture.contract.v1.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadsListResponse {

    private List<UploadsUserResponse> uploadsUserResponseList;
    private int size;


}