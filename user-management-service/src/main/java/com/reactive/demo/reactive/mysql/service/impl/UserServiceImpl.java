package com.reactive.demo.reactive.mysql.service.impl;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.UserService;
import com.reactive.demo.reactive.mysql.service.exception.UserServiceException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    public Mono<UserDto> updateUser(Mono<UserDto> userDto, Long id) {
        log.info("updateUser");
        log.info("id {}", id);
        String email = userDto.toFuture().get().email();
        User userWithEmail = findByEmail(email);
        if (Objects.nonNull(userWithEmail)) {
            return userWithEmail.getId().equals(id) ? Mono.error(new UserServiceException("The same email")) :
                    Mono.error(new UserServiceException("Email is already taken"));
        } else {
            return userRepository.findById(id)
                    .flatMap(user -> userDto.map(UserMapper::mapDtoToEntity))
                    .doOnNext(user -> user.setId(id)).flatMap(userRepository::save).map(UserMapper::mapEntityToDto);
        }
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
        log.info("updateEmail");
        User userWithEmail = findByEmail(updateEmailRequest.getEmail());
        //replaced if statements
        return Objects.nonNull(userWithEmail) ?
                userWithEmail.getId().equals(updateEmailRequest.getId()) ? Mono.error(
                        new UserServiceException("The same email"))
                        : Mono.error(new UserServiceException("Email is already taken"))
                : userRepository.findById(updateEmailRequest.getId())
                        .flatMap(user -> userRepository.updateEmail(updateEmailRequest.getEmail(), user.getId()));
    }

    @SneakyThrows
    private User findByEmail(String email) {
        log.info("findByEmail");
        // do not really know how to do the email checking without blocking. just block() does not work with the current version of netty.
        return userRepository.findByEmail(email).toFuture().get();
    }
}
