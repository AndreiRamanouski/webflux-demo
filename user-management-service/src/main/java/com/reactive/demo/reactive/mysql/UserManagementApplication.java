package com.reactive.demo.reactive.mysql;

import com.reactive.demo.reactive.mysql.config.RabbitHistoryConfigData;
import com.reactive.demo.reactive.mysql.entity.User;
import com.reactive.demo.reactive.mysql.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties({RabbitHistoryConfigData.class})
public class UserManagementApplication {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
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

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
