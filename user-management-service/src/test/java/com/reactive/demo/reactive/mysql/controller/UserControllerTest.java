package com.reactive.demo.reactive.mysql.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.reactive.demo.reactive.mysql.dto.UserDto;
import com.reactive.demo.reactive.mysql.model.UpdateEmailRequest;
import com.reactive.demo.reactive.mysql.model.UserRequest;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import com.reactive.demo.reactive.mysql.service.UserService;
import com.reactive.demo.reactive.mysql.util.RabbitHistoryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest(UserController.class)
class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RabbitHistoryUtil rabbitHistoryUtil;

    @Test
    void saveUser() {
        UserRequest userRequest = getUserRequest();
        Mono<UserDto> userDtoMono = Mono.just(new UserDto(1L, "firstname",
                "lastname", "email", "status"));
        when(userService.saveUser(userDtoMono)).thenReturn(userDtoMono);
        webTestClient.post().uri("/users").body(Mono.just(userRequest), UserRequest.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    void updateUser() {
        Long id = 10L;
        UserRequest userRequest = getUserRequest();
        Mono<UserDto> userDtoMono = Mono.just(new UserDto(1L, "firstname",
                "lastname", "email", "status"));
        when(userService.updateUser(userDtoMono, id)).thenReturn(userDtoMono);
        webTestClient.put().uri("/users/" + id).body(Mono.just(userRequest), UserDto.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    void updateEmail() {
        Long id = 1L;
        UpdateEmailRequest updateEmailRequest = UpdateEmailRequest.builder()
                .id(id)
                .email("email")
                .build();
        given(userService.updateEmail(updateEmailRequest)).willReturn(Mono.empty());
        webTestClient.patch().uri("/users").body(Mono.just(updateEmailRequest), Void.class)
                .exchange().expectStatus().isOk();
    }

    @Test
    void deleteUser() {
        Long id = 1L;
        given(userService.deleteUser(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/users/" + id).exchange().expectStatus().isOk();
    }

    @Test
    void findByUserId() {
        Long id = 1L;
        Mono<UserDto> userDtoMono = Mono.just(new UserDto(1L, "firstname",
                "lastname", "email", "status"));
        when(userService.getByUserId(id)).thenReturn(userDtoMono);
        Flux<UserDto> responseBody = webTestClient.get().uri("/users/" + id).exchange().expectStatus().isOk()
                .returnResult(UserDto.class).getResponseBody();
        StepVerifier.create(responseBody).expectSubscription()
                .expectNextMatches(e -> e.id().equals(1L))
                .verifyComplete();
    }

    private static UserRequest getUserRequest() {
       return UserRequest.builder()
                .email("email")
                .firstName("firstname")
                .lastName("lastname")
                .status("status")
                .build();
    }
}