package com.reactive.demo.reactive.mysql.controller;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.model.EmailNotificationRequest;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import com.reactive.demo.reactive.mysql.model.UserRequest;
import com.reactive.demo.reactive.mysql.service.EmailNotificationService;
import com.reactive.demo.reactive.mysql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final EmailNotificationService emailNotificationService;

    @PostMapping
    public Mono<UserDto> saveUser(@RequestBody UserRequest userRequest) {
        log.info("saveUser");
        return userService.saveUser(Mono.just(UserMapper.mapRequestToDto(userRequest)));
    }

    @PutMapping("/{id}")
    public Mono<UserDto> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        log.info("updateUser");
        return userService.updateUser(Mono.just(UserMapper.mapRequestToDto(userRequest)), id);
    }

    @PatchMapping
    public Mono<Void> updateEmail(@RequestBody UpdateEmailRequest updateEmailRequest) {
        log.info("updateEmail");
        return userService.updateEmail(updateEmailRequest);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        log.info("deleteUser");
        return userService.deleteUser(id);
    }

    @GetMapping("/{userId}")
    public Mono<UserDto> findByUserId(@PathVariable String userId) {
        log.info("findAllByUserId");
        emailNotificationService.sendEmailToAllUsers(EmailNotificationRequest.builder()
                        .body("ss")
                        .title("sss")
                .build());
        return userService.getByUserId(Long.valueOf(userId));
    }
}
