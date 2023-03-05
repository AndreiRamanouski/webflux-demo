package com.reactive.demo.reactive.mysql.controller;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.mapper.UserMapper;
import com.reactive.demo.reactive.mysql.model.UserRequest;
import com.reactive.demo.reactive.mysql.service.UserService;
import java.util.List;
import java.util.concurrent.ExecutionException;
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

    @PostMapping
    public UserDto saveUser(@RequestBody UserRequest userRequest)
            throws ExecutionException, InterruptedException {
        log.info("saveUser");
        return userService.saveUser(Mono.just(UserMapper.mapRequestToDto(userRequest))).toFuture().get();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id)
            throws ExecutionException, InterruptedException {
        log.info("updateUser");
        return userService.updateUser(Mono.just(UserMapper.mapRequestToDto(userRequest)), id).toFuture().get();
    }

    @PatchMapping("/{userId}")
    public UserDto updateEmail(@PathVariable String userId) {
        log.info("updateEmail");
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("deleteUser");
        userService.deleteUser(id);
    }

    @GetMapping("/{userId}")
    public List<UserDto> findAllByUserId(@PathVariable String userId) throws ExecutionException, InterruptedException {
        log.info("findAllByUserId");
        return userService.getAllByUserId(userId).collectList().toFuture().get();
    }
}
