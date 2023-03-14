package com.reactive.demo.reactive.mysql.dto;

public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      String email,
                      String status) {

}
