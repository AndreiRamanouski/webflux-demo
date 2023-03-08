package com.reactive.demo.reactive.mysql.service;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDto> saveUser(Mono<UserDto> userDto);

    Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id);

    Mono<Void> deleteUser(Long id);

    Flux<UserDto> getAllByUserId(String userId);

    Mono<Void> updateEmail(UpdateEmailRequest updateEmailRequest);
}
