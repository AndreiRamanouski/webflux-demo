package com.reactive.demo.reactive.mysql;

import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ReactiveMysqlApplication {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMysqlApplication.class, args);
    }

    @PostConstruct
    private void saveUserAndHistory() {
        for (int i = 0; i < 2; i++) {
            log.info("Saving user id {}", i);
            userRepository.save(User.builder()
                    .firstName("user")
                    .lastName("user")
                    .email("Mock email")
                    .status("Mock Status")
                    .build()).toFuture();
        }
    }
}
