package com.reactive.demo.runner;


import com.reactive.demo.HistoryModel;
import com.reactive.demo.UserAction;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final MappingJackson2MessageConverter mappingJackson2MessageConverter;
    private final RabbitMessagingTemplate rabbitTemplate;


    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.setMessageConverter(mappingJackson2MessageConverter);
        for (int i = 0; i < 10; i++) {
            HistoryModel build = HistoryModel.builder()
                    .userId(String.valueOf(new Random().nextInt(10000000)))
                    .userAction(UserAction.REGISTRATION)
                    .build();
            log.info("Sending message... {}", build);
            rabbitTemplate.convertAndSend("spring-boot-exchange", "spring-boot", build);
            Thread.sleep(1000);
        }
    }

}
