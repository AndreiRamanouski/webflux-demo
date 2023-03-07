package com.reactive.demo.reactive.mysql.dto;

public record UserDto(Long id,
                      String userId,
                      String deviceId,
                      String email,
                      String status) {

}
