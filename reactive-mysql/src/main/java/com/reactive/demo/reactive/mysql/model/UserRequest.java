package com.reactive.demo.reactive.mysql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequest {

    private String userId;
    private String deviceId;
    private String email;
    private String status;
}
