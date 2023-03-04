package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Mono<UserDto> saveUser(Mono<UserDto> userDto) {
        return null;
    }

    @Override
    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id) {
        return null;
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return null;
    }

    @Override
    public Flux<UserDto> getAllByUserId(String userId) {
        return null;
    }
}
