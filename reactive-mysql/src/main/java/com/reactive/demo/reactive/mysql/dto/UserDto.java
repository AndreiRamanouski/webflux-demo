package com.reactive.demo.reactive.mysql.dto;

import com.reactive.demo.reactive.mysql.entity.User;

public record UserDto(Long id,
                      String userId,
                      String subscriptionId,
                      String email,

                              String status) {

}
