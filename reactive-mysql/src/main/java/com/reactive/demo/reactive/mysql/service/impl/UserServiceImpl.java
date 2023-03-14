package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        log.info("id {}", id);
        return userRepository.findById(id).log().flatMap(user -> userDto.map(UserMapper::mapDtoToEntity))
                .doOnNext(user -> user.setId(id)).flatMap(userRepository::save).map(UserMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        log.info("deleteUser");
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<UserDto> getByUserId(Long id) {
        log.info("getByUserId");
        return userRepository.findById(id).map(UserMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> updateEmail(UpdateEmailRequest updateEmailRequest) {
        return userRepository.findById(updateEmailRequest.getId())
                .flatMap(user -> userRepository.updateEmail(updateEmailRequest.getEmail(), user.getId()));
    }
}
