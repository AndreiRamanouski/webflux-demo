package com.reactive.demo;


import com.reactive.demo.config.RabbitProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitProducerConfig.class)
public class RabbitProducerService {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProducerService.class, args);
    }
}
