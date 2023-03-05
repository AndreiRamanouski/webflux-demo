package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDto> saveUser(Mono<UserDto> userDto) {
        log.info("saveUser");
        return userDto.map(UserMapper::mapDtoToEntity).flatMap(userRepository::save).map(UserMapper::mapEntityToDto);
    }

    @Override
    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id) {
        log.info("updateUser");
        return userRepository.findById(id).flatMap(user -> userDto.map(UserMapper::mapDtoToEntity))
                .doOnNext(user -> user.setId(id)).map(UserMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        log.info("deleteUser");
        return userRepository.deleteById(id);
    }

    @Override
    public Flux<UserDto> getAllByUserId(String userId) {
        log.info("getAllByUserId");
        return userRepository.findAllByUserId(userId).map(UserMapper::mapEntityToDto);
    }
}
