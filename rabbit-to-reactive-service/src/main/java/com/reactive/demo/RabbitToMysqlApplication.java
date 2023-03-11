package com.reactive.demo;


import com.reactive.demo.config.RabbitConsumerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConsumerConfig.class)
public class RabbitToMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitToMysqlApplication.class, args);
    }
}
