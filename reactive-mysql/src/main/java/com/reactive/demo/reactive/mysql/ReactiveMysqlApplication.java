package com.reactive.demo.reactive.mysql;

import com.reactive.demo.reactive.mysql.entity.History;
import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.repository.HistoryRepository;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ReactiveMysqlApplication {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMysqlApplication.class, args);
    }

    @PostConstruct
    private void saveUserAndHistory() {
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < 2; i++) {
            String userId = String.valueOf(secureRandom.nextInt(100000));
            log.info("Saving user id {}", i);
            historyRepository.save(History.builder()
                    .userId(userId)
                    .payload("Mock Payload")
                    .build()).toFuture();
            userRepository.save(User.builder()
                    .userId(userId)
                    .deviceId(String.valueOf(secureRandom.nextInt(100000)))
                    .email("Mock email")
                    .status("Mock Status")
                    .build()).toFuture();

        }

    }
}
